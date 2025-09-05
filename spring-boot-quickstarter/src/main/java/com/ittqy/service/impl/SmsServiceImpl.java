package com.ittqy.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.ittqy.config.CloopenConfig;
import com.ittqy.service.SmsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: 27258
 * @Date: 2025/9/5
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    @Resource
    private CloopenConfig cloopenConfig;
    @Override
    public void sendSms(String phone) {
        int code = ThreadLocalRandom.current().nextInt(1000,9999);
        log.info("发送验证码: {}",code);

        //  2. 获取配置信息
        String serverIp = cloopenConfig.getServerIp();
        String serverPort = cloopenConfig.getPort();
        String accountSId = cloopenConfig.getAccountSId();
        String accountToken = cloopenConfig.getAccountToken();
        String appId = cloopenConfig.getAppId();
        String templateId = cloopenConfig.getTemplateId();

        //  3. 创建sdk对象
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String[] datas={String.valueOf(code),"1"};

        //  发送短信
        HashMap<String, Object> result = sdk.sendTemplateSMS(phone, templateId, datas,"1234", UUID.randomUUID().toString());
        if("000000".equals(result.get("statusCode"))){
            //  发送成功
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                log.info("{} = {}",key,object);
            }
        }else{
            //  发送失败
            log.error("错误码={} 错误信息= {}",result.get("statusCode"),result.get("statusMsg"));
        }
    }
}
