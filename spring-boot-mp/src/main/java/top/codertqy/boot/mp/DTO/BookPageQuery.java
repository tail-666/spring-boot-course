package top.codertqy.boot.mp.DTO;

import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 * @Description: 图书分页查询参数
 */
@Data
public class BookPageQuery {
    private int page;
    private int size;
    private String title;
    private String author;

    private String sortField;

    // 排序顺序：asc 或 desc
    private String sortOrder = "asc"; // 默认升序
}
