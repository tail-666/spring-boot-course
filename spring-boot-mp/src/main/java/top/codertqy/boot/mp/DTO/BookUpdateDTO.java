package top.codertqy.boot.mp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 * @Description: 图书更新参数
 */
@Data
public class BookUpdateDTO {
    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "书名不能为空")
    private String title;

    private String author;
    private String isbn;
    private String category;
}
