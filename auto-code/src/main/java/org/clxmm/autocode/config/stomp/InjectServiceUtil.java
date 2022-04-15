package org.clxmm.autocode.config.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * pushMessage (单例)
 */
@Component
public class InjectServiceUtil {


    @Autowired
    private PushMessage pushMessage;

    @PostConstruct
    public void init() {
        InjectServiceUtil.getInstance().pushMessage = this.pushMessage;
    }

    /**
     * 实现单例 start
     */
    private static class SingletonHolder {
        private static final InjectServiceUtil INSTANCE = new InjectServiceUtil();
    }

    private InjectServiceUtil() {

    }

    public static final InjectServiceUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 实现单例 end
     */
    public PushMessage pushMessage() {
        return InjectServiceUtil.getInstance().pushMessage;
    }


}
