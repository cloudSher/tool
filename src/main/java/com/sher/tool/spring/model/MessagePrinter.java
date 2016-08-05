package com.sher.tool.spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cloudsher on 2016/8/4.
 */
@Component
public class MessagePrinter {

    private MessageService messageService;

    @Autowired
    public MessagePrinter(MessageService messageService){
        this.messageService = messageService;
    }

    public void printMessage(String msg){
        System.out.println(messageService.message(msg));
    }
}
