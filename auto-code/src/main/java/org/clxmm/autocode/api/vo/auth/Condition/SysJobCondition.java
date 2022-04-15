package org.clxmm.autocode.api.vo.auth.Condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.clxmm.autocode.api.vo.common.CommonPage;



@ApiModel("定时任务查询条件")
@Data
public class SysJobCondition extends CommonPage {


    @ApiModelProperty(value = "bean的名称")
    private String beanName;


    @ApiModelProperty(value = "任务状态")
    private Integer jobStatus;

}
