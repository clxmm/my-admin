package org.clxmm.autocode.api.vo.auth;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.clxmm.autocode.api.config.valid.pass.PasswordValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 *  用户信息vo
 */
@ApiModel("用户信息")
@Data
public class AdminPassVo {

    @ApiModelProperty(value = "更新时传入id")
    private Long id;

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "密码,新增时必填")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date loginTime;





}
