package top.ittqy.boot.redis.service;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */

public interface SmsService {
    /**
     * 发送短信
     * @param phone 手机号
     * @return boolean
     */
    boolean sendSms(String phone);
}
