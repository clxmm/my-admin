package org.clxmm.autocode.api.vo.auth.Condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.clxmm.autocode.api.vo.common.CommonPage;

/**
 *
 */
@ApiModel("后台用户查询条件")
@Data
public class AdminCondition extends CommonPage {


    @ApiModelProperty(value = "用户名")
    private String username;


}
