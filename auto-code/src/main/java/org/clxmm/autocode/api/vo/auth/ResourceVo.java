package org.clxmm.autocode.api.vo.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@ApiModel(value = "Resource对象", description = "后台资源表")
@Data
public class ResourceVo {
    private Long id;

    @ApiModelProperty(value = "资源名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "资源URL")
    @NotBlank
    private String url;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "资源分类ID")
    @NotBlank
    private Long categoryId;


    private String parentId;

    public List<ResourceVo> children;

}
