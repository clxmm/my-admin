package org.clxmm.autocode.api.vo.stomp;

import lombok.Data;

/**
 * 推送消息的实体类
 */
@Data
public class Message {

    /**
     * 消息编码
     */
    private String code;

    /**
     * 来自（保证唯一）
     */
    private String form;

    /**
     * 去自（保证唯一）
     */
    private String to;

    /**
     * 内容
     */
    private String content;


}
