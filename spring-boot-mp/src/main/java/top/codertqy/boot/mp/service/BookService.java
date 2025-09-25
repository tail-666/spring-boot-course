package top.codertqy.boot.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.codertqy.boot.mp.entity.Book;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 */

public interface BookService extends IService<Book> {
    /**
     * 恢复逻辑删除
     */
    Integer restore(Long id);
}
