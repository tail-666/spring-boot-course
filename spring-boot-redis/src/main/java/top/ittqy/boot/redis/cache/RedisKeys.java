package top.ittqy.boot.redis.cache;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 * @Description: 以某种格式存储
 */
public class RedisKeys {
    /**
     * 验证码 key
     */
    public static String getSmsKey(String phone){
        return "sms:captcha" + phone;
    }

}
