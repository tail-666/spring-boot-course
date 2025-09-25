package top.codertqy.boot.mp.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 * @Description: 给用户展示图书信息
 */
@Data
public class BookVO {
    private Long id;
    private String title;
    private String author;
    private Integer stock;
    private String status;
    private String category;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String updateTime;
}
