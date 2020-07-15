package edu.xau.info.common;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author: 杨斌
 * @Date: 2020/6/3 0003 15:35
 */
@Component
@Slf4j
public class OSSTemplate {

    @Value("${oss.endpoint}")
    String endpoint;
    @Value("${oss.accessKeyId}")
    String accessKeyId;
    @Value("${oss.accessKeySecret}")
    String accessKeySecret;
    @Value("${oss.bucket}")
    String bucket;

    public String upload(String filename, InputStream stream) {
        try {

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            /*OSS ossClient = new OSSClientBuilder().build("http://oss-cn-chengdu.aliyuncs.com",
                    "LTAI4FdoWthCwbevHQ4SbAzE",
                    "fdHS3dni8YyZEDPbh0FFQRTtL4Pcp3");*/
            // 上传文件流。
//            InputStream inputStream = new FileInputStream("E:\\杨斌\\图片\\a.jpg");
            ossClient.putObject(bucket, "pic/" + filename, stream);
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传失败:{}",filename);
            return null;
        }
        String filePath = "https://" + bucket + ".oss-cn-chengdu.aliyuncs.com/pic/" + filename;
        log.info("上传成功: {}", filePath);
        return filePath;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new OSSTemplate().upload("test", new FileInputStream("E:\\杨斌\\图片\\a.jpg"));
    }
}
