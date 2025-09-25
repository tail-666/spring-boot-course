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
@Builder
public class Student {
    private String name;
    private Integer age;
    private Address address;
}
