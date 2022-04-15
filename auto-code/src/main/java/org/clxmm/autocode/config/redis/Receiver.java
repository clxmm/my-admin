package org.clxmm.autocode.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {



    public void receiveMessage(String message) {
//        TestService testService=new TestService();
        //testService.getData();
        log.info("Received <" + message + ">");

    }


}
