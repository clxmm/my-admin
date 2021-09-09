package org.clxmm.myadminjava.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author clxmm
 * @Description
 * @create 2021-09-05 2:42 下午
 */
@Data
public class LoginDto {


    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String code;

    @NotBlank
    private String imageCodeKey;




}
