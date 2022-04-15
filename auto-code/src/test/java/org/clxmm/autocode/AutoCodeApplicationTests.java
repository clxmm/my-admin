package org.clxmm.autocode;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import org.clxmm.autocode.api.vo.stomp.Message;
import org.clxmm.autocode.autocode.entity.Admin;
import org.clxmm.autocode.autocode.entity.Resource;
import org.clxmm.autocode.autocode.service.ResourceService;
import org.clxmm.autocode.autocode.service.SysJobService;

import org.clxmm.autocode.system.security.config.IgnoreUrlsConfig;
import org.clxmm.autocode.system.security.util.JwtTokenUtil;
import org.clxmm.autocode.system.security.dto.AdminUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class AutoCodeApplicationTests {

    @Test
    void contextLoads() {
    }



    @Autowired
    RedisTemplate redisTemplate;


    @Autowired
    IgnoreUrlsConfig ignoreUrlsConfig;



    @Test
    public void test1() {

        String s = ignoreUrlsConfig.staticUrls().toString();
        System.out.println(s);


    }


    @Test
    public void testRedis() {
        Message message = new Message();
        message.setContent("weeor");
        message.setForm("system");
        message.setTo("12345");
        redisTemplate.opsForValue().set("testredis",message);
        String messageJson = JSON.toJSONString(message);
        redisTemplate.opsForValue().set("testRedis",messageJson);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void createPassword() {

        System.out.println(passwordEncoder.encode("123"));
    }


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    public void testJWT() {
        Admin admin = new Admin();
        admin.setUsername("test");
        AdminUserDetails userDetails = new AdminUserDetails(admin,null);

        System.out.println(userDetails.getUsername());

        // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiY3JlYXRlZCI6MTYzMDk4NDk3MzYyNywiZXhwIjoxNjMxNTg5NzczfQ.iomdJn007T0VUq7ktZ0U_fL7PnUgAueo2Mb6WfwsDN5MG9B4Jtw2wl5yNLDI0Bs95zFEoRjdHAEtTnoB1rCybg
        String s = jwtTokenUtil.generateToken(userDetails);
        System.out.println(s);
    }




    @Autowired
    ResourceService resourceService;


    @Test
    public void testR() {
        List<Resource> resourceList = resourceService.getResourceList(1L);
        resourceList.stream().forEach(System.out::println);


    }


    @Autowired
    SysJobService sysJobService;

    @Test
    public void test11() {

     //   sysJobService.changeJobStatus(0,0);

        sysJobService.getAll();

    }




//    @Autowired
//    BookRepositry bookRepositry;
//
//
//    @Test
//    public void testEl1() {
//        Book book = new Book();
//        book.setId(4);
//        book.setBookName("西游记");
//        book.setAuto("罗贯中");
//        bookRepositry.save(book);
//    }


//    @Test
//    public void testE2() {
//        List<Book> books = bookRepositry.findBooksByBookNameLike("西");
//        books.forEach(System.out::println);
//        Iterable<Book> all = bookRepositry.findAll();
//        all.forEach(System.out::println);
//    }






}
