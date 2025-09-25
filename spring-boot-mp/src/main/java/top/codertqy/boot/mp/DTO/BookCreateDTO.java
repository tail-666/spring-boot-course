package top.codertqy.boot.mp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 * @Description: 创建图书的参数
 */
@Data
public class BookCreateDTO {
    @NotBlank(message = "书名不能为空")
    private String title;
    private String author;
    private String isbn;
    private String category;

    @NotNull(message = "库存不能为空")
    private Integer stock;
}
