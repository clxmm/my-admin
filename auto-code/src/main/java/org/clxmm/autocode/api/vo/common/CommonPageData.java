package org.clxmm.autocode.api.vo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class CommonPageData extends CommonPage {


    @ApiModelProperty("数据")
    private Collection data;
}
