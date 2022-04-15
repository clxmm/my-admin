package org.clxmm.autocode.system.idempotent.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.clxmm.autocode.common.exception.ServiceException;
import org.clxmm.autocode.common.exception.enums.GlobalErrorCodeConstants;
import org.clxmm.autocode.system.idempotent.annotation.Idempotent;
import org.clxmm.autocode.system.idempotent.keyresolver.IdempotentKeyResolver;
import org.clxmm.autocode.system.idempotent.redis.IdempotentRedisDAO;
import org.clxmm.autocode.util.collection.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;


/**
 * 拦截声明了 {@link Idempotent} 注解的方法，实现幂等操作
 *
 * @author 芋道源码
 */
@Aspect
@Slf4j
@Component
public class IdempotentAspect {

    /**
     * IdempotentKeyResolver 集合
     */
    private final Map<Class<? extends IdempotentKeyResolver>, IdempotentKeyResolver> keyResolvers;

    private final IdempotentRedisDAO idempotentRedisDAO;

    public IdempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        this.keyResolvers = CollectionUtils.convertMap(keyResolvers, IdempotentKeyResolver::getClass);
        this.idempotentRedisDAO = idempotentRedisDAO;
    }


    @Before("@annotation(idempotent)")
    public void beforePointCut(JoinPoint joinPoint, Idempotent idempotent) {
        log.info("IdempotentAspect start");
        // 获得 IdempotentKeyResolver
        Class<? extends IdempotentKeyResolver> key1 = idempotent.keyResolver();
        log.info("key1:" + key1);


        IdempotentKeyResolver keyResolver = keyResolvers.get(key1);
        Assert.notNull(keyResolver, "找不到对应的 IdempotentKeyResolver");
        // 解析 Key
        String key = keyResolver.resolver(joinPoint, idempotent);
        log.info(key);

        // 锁定 Key。
        boolean success = idempotentRedisDAO.setIfAbsent(key, idempotent.timeout(), idempotent.timeUnit());
        // 锁定失败，抛出异常
        if (!success) {
            log.info("[beforePointCut][方法({}) 参数({}) 存在重复请求]", joinPoint.getSignature().toString(), joinPoint.getArgs());
            throw new ServiceException(GlobalErrorCodeConstants.REPEATED_REQUESTS.getCode(), idempotent.message());
        }
    }


}
