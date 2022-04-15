package org.clxmm.autocode.api.vo.auth;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysJob对象", description = "定时任务管理表")
public class SysJobVo  implements Serializable {


    @ApiModelProperty(value = "id")
    private Integer jobId;

    @ApiModelProperty(value = "bean的名称")
    @NotBlank
    private String beanName;

    @ApiModelProperty(value = "cron表达式")
    @NotBlank
    private String cronExpression;

    @ApiModelProperty(value = "任务状态")
    private Integer jobStatus;

    @ApiModelProperty(value = "方法名称")
    @NotBlank
    private String methodName;

    @ApiModelProperty(value = "方法参数")
    private String methodParams;

    @ApiModelProperty(value = "备注")
    private String remark;




}
