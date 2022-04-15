package org.clxmm.autocode.api.vo.auth.Condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.clxmm.autocode.api.vo.common.CommonPage;


@ApiModel("角色分页查询条件")
@Data
public class RoleCondition extends CommonPage {
    @ApiModelProperty("角色名称")
    private String roleName;
}
