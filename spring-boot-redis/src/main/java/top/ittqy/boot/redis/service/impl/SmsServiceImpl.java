package top.ittqy.boot.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ittqy.boot.redis.cache.RedisCache;
import top.ittqy.boot.redis.cache.RedisKeys;
import top.ittqy.boot.redis.config.CloopenConfig;
import top.ittqy.boot.redis.entity.User;
import top.ittqy.boot.redis.enums.ErrorCode;
import top.ittqy.boot.redis.exception.ServerException;
import top.ittqy.boot.redis.mapper.UserMapper;
import top.ittqy.boot.redis.service.SmsService;
import top.ittqy.boot.redis.utils.CommonUtils;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */
@Slf4j
@Service
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final RedisCache redisCache;
    private final CloopenConfig cloopenConfig;
    private final UserMapper userMapper;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return 发送成功返回true，发送失败返回false
     */
    @Override
    public boolean sendSms(String phone) {
        // 拿到用户的手机号，判断是否在数据库中
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        Long count = userMapper.selectCount(queryWrapper);
        // 如果没有查询到，则返回手机号不存在
        if (count == 0) {
            throw new ServerException(ErrorCode.PHONE_NOT_EXIST);
        }

        // 1. 校验手机号
        if (!CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PHONE_ERROR);
        }
        // 2. 生成验证码，并且存入Redis，60s有效
        int code = CommonUtils.generateCode();
        redisCache.set(RedisKeys.getSmsKey(phone), code, 60);
        log.info("发送验证码: {}", code);
        boolean flag=true;
        flag=send(phone,code);
        return flag;
    }

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @param code 验证码
     * @return 发送高层个返回true，失败返回false
     */
    public boolean send(String phone, int code) {
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
            return false;
        }
        return true;
    }

}
