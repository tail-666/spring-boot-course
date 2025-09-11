package top.codertqy.config.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @Author: 27258
 * @Date: 2025/9/11
 */

@Data
@Component
public class Team {
    @Value("${team.leader}")
    @NotNull(message = "leader不能为空")
    @Length(min = 2, max = 10, message = "leader长度必须在2-5之间")
    private String leader;

    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号格式不正确")
    private String phone;

    @Value("${team.age}")
    @Max(5)
    @Min(1)
    private Integer age;

    @Value("${team.createDate}")
    @Past(message = "createDate必须是过去的时间")
    private LocalDate createDate;
}
