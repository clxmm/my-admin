package org.clxmm.autocode.api.vo.common;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用，分页条件
 */
@Data
public class CommonPage {

    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "每页显示条数，默认 10", example = "10")
    private long pageSize = 10;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页，默认 1", example = "1")
    private long current = 1;


    /**
     * 总数
     */
    @ApiModelProperty(value = "总数", example = "1")
    private long total = 0;

}
