package org.clxmm.autocode.system.idempotent.keyresolver;

import org.aspectj.lang.JoinPoint;
import org.clxmm.autocode.system.idempotent.annotation.Idempotent;

public interface IdempotentKeyResolver {

    /**
     * 解析一个 Key
     *
     * @param idempotent 幂等注解
     * @param joinPoint  AOP 切面
     * @return Key
     */
    String resolver(JoinPoint joinPoint, Idempotent idempotent);

}
