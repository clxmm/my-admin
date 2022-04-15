package org.clxmm.autocode.common;


import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.autocode.common.exception.ErrorCode;

@Data
@ApiModel(value = "返回结果封装", description = "返回结果")
public class Result {


    @ApiModelProperty(value = "响应码")
    private String code = "200";

    @ApiModelProperty(value = "响应数据")
    private Object data;

    @ApiModelProperty(value = "响应消息")
    private String msg = "ok";

    public Result() {

    }

    public Result(String code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    public static Result ok() {
        Result result = new Result("200", null, "ok!");
        return result;
    }

    /**
     * 如果id为空，则为添加，否则，为更新
     * @param id
     * @return
     */
    public static Result okAddOrUpdate(Object id) {
        if (!ObjectUtil.isEmpty(id)) {
            return new Result("200", null, "更新成功");
        }
        Result result = new Result("200", null, "添加成功");
        return result;
    }

    public static Result ok(String msg) {
        Result result = new Result("200", null, msg);
        return result;
    }

    public static Result ok(Object o) {
        Result result = new Result("200", o, "ok!");
        return result;
    }

    public static Result ok(Object o, String msg) {
        Result result = new Result("200", o, msg);
        return result;
    }

    public static Result okSaveOrUpdateSuccess() {
        Result result = new Result("200", null, "操作成功");
        return result;
    }

    public static Result okDelSuccess() {
        Result result = new Result("200", null, "删除成功");
        return result;
    }

    public static Result okDelFail() {
        Result result = new Result("200", null, "删除失败");
        return result;
    }

    public static Result error(Object o, String msg) {
        Result result = new Result("500", o, msg);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result("500", null, msg);
        return result;
    }


    public static Result error(ErrorCode errorCode) {
        Result result = new Result(errorCode.getCode() + "", null, errorCode.getMsg());
        return result;
    }


    private static Result getResult() {
        return new Result();
    }


}
