package top.codertqy.boot.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codertqy.boot.mp.entity.Book;
import top.codertqy.boot.mp.mapper.BookMapper;
import top.codertqy.boot.mp.service.BookService;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 */
@Service
@Transactional
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Override
    public Integer restore(Long id) {
        return bookMapper.restore(id);
    }
}
