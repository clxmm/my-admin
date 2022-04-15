package org.clxmm.autocode.autocode.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.clxmm.autocode.autocode.common.entity.CommonEntityId;

/**
 * <p>
 *
 * </p>
 *
 * @author clxmmTest
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UploadFile对象", description = "")
public class UploadFile extends CommonEntityId {


    @ApiModelProperty(value = "文件的原始名称")
    private String originalName;

    @ApiModelProperty(value = "上传服务器的名称")
    private String newName;

    private String bucket;

    @ApiModelProperty(value = "文件完整路径名称")
    @TableField("object_key")
    private String objectKey;

    @ApiModelProperty(value = "文件url")
    private String url;

    @ApiModelProperty(value = "服务器标志：1，上传服务器")
    private int upFlag = 1;

    private String contentType;


}
