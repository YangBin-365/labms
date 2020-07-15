package edu.xau.info.common;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;



/**
 * @Author: 杨斌
 * @Date: 2020/6/1 0001 13:03
 */
@Component
@Slf4j
public class SendMsgTemplate {

    @Value("${mes.signName}")
    private String signName;

    @Value("${mes.accessKeyId}")
    private String accessKeyId;

    @Value("${mes.accessSecret}")
    private String accessSecret;

    public AppResponse<String> sendCheckCode(Map<String, String> map) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", signName);

        request.putQueryParameter("PhoneNumbers", map.get("PhoneNumbers"));
        request.putQueryParameter("TemplateCode", map.get("TemplateCode"));
        request.putQueryParameter("TemplateParam", map.get("TemplateParam"));
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(response.getData());
            return AppResponse.ok(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return AppResponse.fail(null);
        }
    }


}

