package com.zistone.gprs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping(value = "/Download")
public class DownloadController {
    private Logger _logger = LoggerFactory.getLogger(DownloadController.class);

    @RequestMapping(value = "/OTATest", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Resource> OTATest(HttpServletRequest request) throws IOException {
        _logger.info("收到下载更新包的请求");
        //待下载文件名
        String fileName = "update.zip";
        File file = new File(fileName);
        if (Objects.nonNull(file)) {
            Resource resource = null;
            String contentType = null;
            try {
                resource = new FileSystemResource(file);
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                _logger.info("已获取到要下载的更新包资源");
            } catch (IOException e) {
                _logger.error(e.getMessage());
            }
            if (Objects.isNull(contentType)) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } else {
            throw new IOException("资源未找到！");
        }
    }


    @RequestMapping(value = "/TxtTest", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Resource> TxtTest(HttpServletRequest request) throws IOException {
        _logger.info("收到下载更新文本的请求");
        //待下载文件名
        String fileName = "update_info.txt";
        File file = new File(fileName);
        if (Objects.nonNull(file)) {
            Resource resource = null;
            String contentType = null;
            try {
                resource = new FileSystemResource(file);
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                _logger.info("已获取到要下载的文本资源");
            } catch (IOException e) {
                _logger.error(e.getMessage());
            }
            if (Objects.isNull(contentType)) {
                contentType = "text/plain";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } else {
            throw new IOException("资源未找到！");
        }
    }

}
