package org.clxmm.autocode.api.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.clxmm.autocode.common.validator.annotation.HaveNoBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@ApiModel(value = "DatasourceConfigVo对象", description = "数据源配置表")
public class DatasourceConfigVo implements Serializable {


    @NotNull
    private Integer id;

    @ApiModelProperty(value = "数据库类型，1：MySql, 2:Oracle, 3:sqlserver",example = "1")
    private Integer dbType;

    @ApiModelProperty(value = "数据库驱动",example = "com.mysql.cj.jdbc.Driver")
    @NotEmpty(message = "数据库驱动不能为空")
    private String driverClass;

    @ApiModelProperty(value = "数据库名称")
    @NotBlank
    @HaveNoBlank
    private String dbName;

    @ApiModelProperty(value = "数据库host")
    private String host;

    @ApiModelProperty(value = "数据库端口")
    @Min(value = 5)
    private Integer port;

    @ApiModelProperty(value = "数据库用户名")
    private String username;

    @ApiModelProperty(value = "数据库密码")
    private String password;

    @ApiModelProperty(value = "包名")
    private String packageName;

    @ApiModelProperty(value = "删除的前缀")
    private String delPrefix;

    @ApiModelProperty(value = "代码生成器模板组id")
    private Integer groupId;






}
