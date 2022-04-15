package org.clxmm.autocode.autocode.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据源配置表
 * </p>
 *
 * @author clxmmTest
 * @since 2021-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "DatasourceConfig对象", description = "数据源配置表")
public class DatasourceConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "数据库类型，1：MySql, 2:Oracle, 3:sqlserver")
    private Integer dbType;

    @ApiModelProperty(value = "数据库驱动")
    private String driverClass;

    @ApiModelProperty(value = "数据库名称")
    private String dbName;

    @ApiModelProperty(value = "数据库host")
    private String host;

    @ApiModelProperty(value = "数据库端口")
    private Integer port;

    @ApiModelProperty(value = "数据库用户名")
    private String username;

    @ApiModelProperty(value = "数据库密码")
    private String password;

    @ApiModelProperty(value = "是否已删除，1：已删除，0：未删除")
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer deleted;

    private String packageName;

    private String delPrefix;

    private Integer groupId;


}
