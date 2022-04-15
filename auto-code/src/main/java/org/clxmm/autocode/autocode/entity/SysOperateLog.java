package org.clxmm.autocode.autocode.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysOperateLog对象", description = "操作日志记录")
public class SysOperateLog implements Serializable {


    /**
     * {@link #javaMethodArgs} 的最大长度
     */
    public static final Integer JAVA_METHOD_ARGS_MAX_LENGTH = 8000;

    /**
     * {@link #resultData} 的最大长度
     */
    public static final Integer RESULT_MAX_LENGTH = 4000;



    @ApiModelProperty(value = "日志主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "链路追踪编号")
    private String traceId;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "模块标题")
    private String module;

    @ApiModelProperty(value = "操作名")
    private String name;

    @ApiModelProperty(value = "操作分类")
    private Long operateType;

    @ApiModelProperty(value = "操作内容")
    private String content;

    @ApiModelProperty(value = "拓展字段")
    private String exts;

    @ApiModelProperty(value = "请求方法名")
    private String requestMethod;

    @ApiModelProperty(value = "请求地址")
    private String requestUrl;

    @ApiModelProperty(value = "用户 IP")
    private String userIp;

    @ApiModelProperty(value = "浏览器 UA")
    private String userAgent;

    @ApiModelProperty(value = "Java 方法名")
    private String javaMethod;

    @ApiModelProperty(value = "Java 方法的参数")
    private String javaMethodArgs;

    @ApiModelProperty(value = "操作时间")
    private Date startTime;

    @ApiModelProperty(value = "执行时长")
    private Integer duration;

    @ApiModelProperty(value = "结果码")
    private Integer resultCode;

    @ApiModelProperty(value = "结果提示")
    private String resultMsg;

    @ApiModelProperty(value = "结果数据")
    private String resultData;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    private String updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;


}
