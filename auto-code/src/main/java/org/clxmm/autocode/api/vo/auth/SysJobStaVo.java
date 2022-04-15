package org.clxmm.autocode.api.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("更改状态")
@Data
public class SysJobStaVo {

    @ApiModelProperty(value = "id")
    private Integer jobId;


    @ApiModelProperty(value = "任务状态")
    private Integer jobStatus;

}
