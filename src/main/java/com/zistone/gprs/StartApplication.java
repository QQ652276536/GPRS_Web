package com.zistone.gprs;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zistone.gprs.service.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//开启定时任务的支持
@EnableScheduling
//@SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
//启用JPA审计(自动填充默认值)
@EnableJpaAuditing
public class StartApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    /**
     * 配置定时任务,用于测试WebSocket服务端主动推送消息
     */
    @Scheduled(fixedRate = 5 * 1000)
    public void Test_WebSocket_Send() {
        try {
            WebSocketServer.InitiativeSendMessage("WebSocket服务端定时主动推送");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置定时任务,用于读取163邮箱的铱星网关发来的邮件
     */
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void FixedRate_EmailService_YX() {
        try {
//            new EmailTask_YX();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置fastjson消息转换器
     * <p>
     * 在这里全局配置后实体类里对应的Date字段不再需要format
     *
     * @return
     */
    @Bean
    public HttpMessageConverters fastjsonHttpMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        //定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //全局时间配置
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        //处理中文乱码
        List<MediaType> mediaTypeList = new ArrayList<MediaType>() {{
            add(MediaType.APPLICATION_JSON_UTF8);
        }};
        //在convert中添加配置信息
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(0, fastJsonHttpMessageConverter);
        return new HttpMessageConverters(true, converters);
    }

}
