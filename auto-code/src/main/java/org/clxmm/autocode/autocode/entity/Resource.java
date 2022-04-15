package org.clxmm.autocode.autocode.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.clxmm.autocode.autocode.common.entity.CommonEntity;
import org.clxmm.autocode.autocode.common.entity.CommonEntityId;

/**
 * <p>
 * 后台资源表
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Resource对象", description = "后台资源表")
public class Resource extends CommonEntityId implements Serializable {

    private static final long serialVersionUID = 1L;


    private String parentId;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源URL")
    private String url;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "资源分类ID")
    private Long categoryId;


}
