package top.ittqy.boot.redis.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */
public class CommonUtils {
    /**
     * 校验手机号
     * @param phone 电话
     * @return boolean
     */
    public static boolean checkPhone(String phone) {
        if(phone==null||phone.length()!=11){
            return false;
        }
        // 中国大陆手机号的正则表达式
        String regex = "^1[3-9]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phone).matches();
    }

    /**
     * 生成4位验证码
     * @return int
     */
    public static int generateCode() {
        return ThreadLocalRandom.current().nextInt(1000,9999);
    }
}
