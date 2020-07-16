package edu.xau.info.controller;

import edu.xau.info.common.AppResponse;
import edu.xau.info.common.OSSTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 10:39
 */
@Slf4j
@Api(tags = "学生模块")
@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    OSSTemplate ossTemplate;

    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public AppResponse<Object> upload(@RequestParam("uploadfile") MultipartFile[] files) {//可不指定
        try {
            log.info("开始上传");
            List<String> paths = new ArrayList<>();
            for (MultipartFile file : files) {
                String filename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + file.getOriginalFilename();
                String filePath = ossTemplate.upload(filename, file.getInputStream());
                paths.add(filePath);
            }
            log.info("上传成功，路径 = {}", paths);
            return AppResponse.ok(paths);
        } catch (IOException e) {
            log.info("上传失败");
            return AppResponse.fail(null);
        }
    }


}
