package com.zistone;

import com.zistone.file_listener.FileData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

//开启定时任务的支持
@EnableScheduling
//@SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
//启用JPA审计(自动填充默认值)
@EnableJpaAuditing
public class StartApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(StartApplication.class, args);

        FileData fileData = new FileData();
        fileData.setM_path("C:\\demo\\gprs_info.txt");
        //fileData.setM_path("C:\\Users\\zistone\\Desktop\\gprs_info.txt");
        fileData.setM_time(5 * 1000 * 60);
        fileData.setM_encode("UTF-8");
        //FileContentEvent fileContentEvent = new FileContentEvent(fileData);
    }

}
