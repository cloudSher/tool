package com.sher.tool.spring;

import com.sher.tool.spring.model.MessagePrinter;
import com.sher.tool.spring.model.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cloudsher on 2016/8/4.
 */
@Configuration
@ComponentScan
public class Application {

    @Bean
    MessageService mockMessageService(){
        return new MessageService() {

            @Override
            public String message(String msg) {
                return msg;
            }
        };
    }

    public static void main(String args[]){
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        MessagePrinter bean = context.getBean(MessagePrinter.class);
        bean.printMessage("hello");
    }

}
