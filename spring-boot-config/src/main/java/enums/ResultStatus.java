package enums;

import lombok.Getter;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */
@Getter
public enum ResultStatus {
    SUCCESS("发送成功"),FAIL("发送失败");

    private final String status;

    ResultStatus(String status){
        this.status = status;
    }
}
