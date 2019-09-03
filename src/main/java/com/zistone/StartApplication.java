package com.zistone;

import com.zistone.file_listener.FileContentEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
//启用JPA审计(自动填充默认值)
@EnableJpaAuditing
public class StartApplication
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(StartApplication.class, args);
        //加载自定义监听
        //configurableApplicationContext.addApplicationListener(new FileContentListener());
        //发布事件
        configurableApplicationContext.publishEvent(new FileContentEvent(new Object()));
    }

}
