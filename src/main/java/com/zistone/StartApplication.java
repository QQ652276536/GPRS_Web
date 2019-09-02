package com.zistone;

import com.zistone.file_listener.FileContentEvent;
import com.zistone.file_listener.FileContentListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
//自动更新时间
@EnableJpaAuditing
public class StartApplication
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(StartApplication.class, args);
        //加载自定义监听
        configurableApplicationContext.addApplicationListener(new FileContentListener());
        //发布事件
        configurableApplicationContext.publishEvent(new FileContentEvent(new Object()));
    }

}
