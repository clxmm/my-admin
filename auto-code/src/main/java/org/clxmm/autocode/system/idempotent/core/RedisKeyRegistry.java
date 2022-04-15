package org.clxmm.autocode.system.idempotent.core;

import java.util.ArrayList;
import java.util.List;

public class RedisKeyRegistry {


    /**
     * Redis RedisKeyDefine 数组
     */
    private static final List<RedisKeyDefine> defines = new ArrayList<>();

    public static void add(RedisKeyDefine define) {
        defines.add(define);
    }

    public static List<RedisKeyDefine> list() {
        return defines;
    }

    public static int size() {
        return defines.size();
    }
}
