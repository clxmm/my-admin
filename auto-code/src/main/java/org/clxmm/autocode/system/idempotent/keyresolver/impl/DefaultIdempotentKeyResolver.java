package org.clxmm.autocode.system.idempotent.keyresolver.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import org.aspectj.lang.JoinPoint;
import org.clxmm.autocode.system.idempotent.annotation.Idempotent;
import org.clxmm.autocode.system.idempotent.keyresolver.IdempotentKeyResolver;
import org.springframework.stereotype.Component;


/**
 * 默认幂等 Key 解析器，使用方法名 + 方法参数，组装成一个 Key
 *
 * 为了避免 Key 过长，使用 MD5 进行“压缩”
 *
 * @author 芋道源码
 */
@Component
public class DefaultIdempotentKeyResolver implements IdempotentKeyResolver {

    @Override
    public String resolver(JoinPoint joinPoint, Idempotent idempotent) {
        // todo 加上用户的id
        String methodName = joinPoint.getSignature().toString();
        String argsStr = StrUtil.join(",", joinPoint.getArgs());
        return SecureUtil.md5(methodName + argsStr);
    }

}
