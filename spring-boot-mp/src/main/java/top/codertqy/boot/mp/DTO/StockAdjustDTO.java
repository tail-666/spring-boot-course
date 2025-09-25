package top.codertqy.boot.mp.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 * @Description: 图书更新参数
 */
@Data
public class StockAdjustDTO {
    /**
     * 调整类型：入库或出库
     */
    @Getter
    public enum AdjustType {
        IN("入库"),
        OUT("出库");

        private final String description;

        AdjustType(String description) {
            this.description = description;
        }

    }

    @NotNull(message = "调整类型不能为空")
    private AdjustType type;

    @NotNull(message = "调整数量不能为空")
    private Integer quantity;
}
