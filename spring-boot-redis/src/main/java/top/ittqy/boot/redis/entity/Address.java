package top.ittqy.boot.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 27258
 * @Date: 2025/9/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// 该注解告诉lombok生成构造函数，getter，setter，toString，hashCode，equals方法
@Builder
public class Address {
    private String province;
    private String city;
}
