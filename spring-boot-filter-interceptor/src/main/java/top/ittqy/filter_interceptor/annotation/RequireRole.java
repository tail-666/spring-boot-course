package top.ittqy.filter_interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 27258
 * @Date: 2025/10/8
 * @Description: 自定义权限注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireRole {
    // 角色名称数组，支持多个
    String[] value();
}