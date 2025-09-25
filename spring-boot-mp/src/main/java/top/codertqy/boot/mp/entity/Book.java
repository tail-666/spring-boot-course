package top.codertqy.boot.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 */
@TableName("book")
@Data
public class Book {
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "书名不能为空")
    private String title;

    private String author;
    private String isbn;
    private String category;

    @NotNull(message = "库存不能为空")
    private Integer stock;

    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
