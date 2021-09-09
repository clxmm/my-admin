package org.clxmm.myadminjava.api;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.myadminjava.common.Result;
import org.clxmm.myadminjava.common.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.Sun;

import javax.validation.Valid;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author clxmm
 * @Description
 * @create 2021-09-04 9:22 下午
 */
@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class AuthController {


    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/captcha")
    public Result captcha() throws IOException {


        CircleCaptcha captcha =
                CaptchaUtil.createCircleCaptcha(180, 100, 4, 4);

        String base64Data = captcha.getImageBase64Data();


        String imgCodeKey = "img" + UUID.fastUUID().toString();

        String code = captcha.getCode();
        redisTemplate.opsForValue().set(imgCodeKey, code, 10, TimeUnit.MINUTES);


        return Result.succ(
                MapUtil.builder()
                        .put("imageCode", imgCodeKey)
                        .put("token", "1233")
                        .put("captchaImg", base64Data)
                        .build()

        );
    }


    @PostMapping("login")
    public Result login(@RequestBody @Valid LoginDto loginDto) {

        System.out.println(loginDto);

        String code = redisTemplate.opsForValue().get(loginDto.getImageCodeKey()).toString();
        if (StrUtil.isBlank(code)) {
            log.error("验证吗已过期");
            return Result.fail("验证吗已过期, 请重新获取");
        }

        if (!code.equals(loginDto.getCode())) {
            log.error("验证码错误");
//            return Result.fail("验证码错误");
        }


        return Result.succ(null);
    }


}
