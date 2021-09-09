package org.clxmm.myadminjava.common;

import lombok.Data;

/**
 * @author clxmm
 * @Description
 * @create 2021-09-04 9:26 下午
 */
@Data
public class Result {


    private int code;
    private String msg;
    private Object data;


    public static Result succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
