package org.clxmm.autocode.learn.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("test1")
    public String test1() {
        int i = 0;

        new Thread(() ->{
            t();
        }).start();

        return  i+ "hello";
    }



    private void t() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
        }
    }






}
