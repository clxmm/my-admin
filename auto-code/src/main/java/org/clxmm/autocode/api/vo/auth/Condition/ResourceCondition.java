package org.clxmm.autocode.api.vo.auth.Condition;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.clxmm.autocode.api.vo.common.CommonPage;

@Data
@ApiModel("资源列表查询条件")
public class ResourceCondition extends CommonPage {

    private String resourceName;

}
