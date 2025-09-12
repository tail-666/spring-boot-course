package enums;

import lombok.Getter;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */

@Getter
public enum ExpressStatus {
    CREATED("已揽收"),
    TRANSIT("在途中"),
    SUCCESS("签收");
    private final String status;

    ExpressStatus(String status) {
        this.status = status;
    }
}
