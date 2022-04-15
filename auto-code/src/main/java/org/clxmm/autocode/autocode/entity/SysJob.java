package org.clxmm.autocode.autocode.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 定时任务管理表
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysJob对象", description = "定时任务管理表")
public class SysJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "job_id",  type = IdType.AUTO)

    private Integer jobId;

    @ApiModelProperty(value = "bean的名称")
    private String beanName;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "任务状态,0开始，1关闭")
    private Integer jobStatus;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "方法参数")
    private String methodParams;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

//    @TableField(strategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "穿件时间")
    private Date createTime;

    @ApiModelProperty(value = "更新者id")
    private Long updateId;

    @ApiModelProperty(value = "创建者id")
    private Long createId;


    @ApiModelProperty(value = "是否删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Integer deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysJob sysJob = (SysJob) o;
        return Objects.equals(beanName, sysJob.beanName) &&
                Objects.equals(methodName, sysJob.methodName) &&
                Objects.equals(methodParams, sysJob.methodParams) &&
                Objects.equals(cronExpression, sysJob.cronExpression) &&
                Objects.equals(deleted, sysJob.deleted);
    }


}
