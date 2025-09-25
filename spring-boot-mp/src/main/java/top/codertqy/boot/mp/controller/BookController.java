package top.codertqy.boot.mp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.codertqy.boot.mp.DTO.BookCreateDTO;
import top.codertqy.boot.mp.DTO.BookPageQuery;
import top.codertqy.boot.mp.DTO.BookUpdateDTO;
import top.codertqy.boot.mp.DTO.StockAdjustDTO;
import top.codertqy.boot.mp.VO.BookVO;
import top.codertqy.boot.mp.common.Result;
import top.codertqy.boot.mp.entity.Book;
import top.codertqy.boot.mp.enums.ErrorCode;
import top.codertqy.boot.mp.exception.ServerException;
import top.codertqy.boot.mp.service.BookService;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 * @Description: 图书控制器
 */
@Slf4j
@RestController
@RequestMapping("/book")
// 全参构造器注入，不需要@Autowired注解/或者@Resource注解来注入
// @AllArgsConstructor
@RequiredArgsConstructor
@Validated
public class BookController {
    private final BookService bookService;

    /**
     * 新增图书
     *
     * @param
     */
    @PostMapping("/")
    public Result<BookVO> createBook(@Valid @RequestBody BookCreateDTO dto) {
        try{
            Book book = new Book();
            // 将DTO属性复制到实体对象 第一个参数是原数据，第二个是目标数据
            BeanUtils.copyProperties(dto, book);

            // 2. 保存到数据库
            bookService.save(book);

            // 3. 查询并转换成功VO
            Book savedBook = bookService.getById(book.getId());
            BookVO bookVO = new BookVO();
            // 将存到的数据复制到VO对象，返回给前端
            BeanUtils.copyProperties(savedBook, bookVO);
            bookVO.setStatus(savedBook.getStock() > 0 ? "可借" : "已借完");
            if (savedBook.getCreateTime() != null) {
                bookVO.setCreateTime(savedBook.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            if (savedBook.getUpdateTime() != null) {
                bookVO.setUpdateTime(savedBook.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            return Result.ok(bookVO);
        }catch (Exception e){
            //  抛出自定义异常ServerException
            throw new ServerException("服务器异常");
        }

    }

    /**
     * 更新图书
     */
    @PutMapping("/{id}")
    public Result<BookVO> updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO dto) {
        // 将updateDTO属性复制到实体对象，然后给id赋值，再去更新数据
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);
        book.setId(id);

        bookService.updateById(book);

        Book savedBook = bookService.getById(id);
        BookVO bookVO = new BookVO();
        /**
         * BeanUtils.copyProperties的局限性
         * 只复制 字段名相同 + 类型兼容 的属性。
         * 不支持自动类型转换（如 Long → String、LocalDateTime → String）。
         * 字段名不一致也不复制（如 bookId vs id）。
         */
        BeanUtils.copyProperties(savedBook, bookVO);
        bookVO.setStatus(savedBook.getStock() > 0 ? "可借" : "已借完");
        if (savedBook.getCreateTime() != null) {
            bookVO.setCreateTime(savedBook.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (savedBook.getUpdateTime() != null) {
            bookVO.setUpdateTime(savedBook.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return Result.ok(bookVO);
    }

    /**
     * 库存调整
     *
     * @param id
     * @param dto
     * @return
     */
    @PatchMapping("/stock/adjust/{id}")
    public Result<BookVO> adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustDTO dto) {
        // 根据ID获取图书实体
        Book book = bookService.getById(id);
        System.out.println(book);
        if (book == null) {
            throw new ServerException("图书不存在");
        }
        // 根据调整类型修改库存
        switch (dto.getType()) {
            case IN:
                book.setStock(book.getStock() + dto.getQuantity());
                break;
            case OUT:
                if (book.getStock() < dto.getQuantity()) {
                    throw new ServerException(ErrorCode.NOT_FOUND);
                }
                book.setStock(book.getStock() - dto.getQuantity());
                break;
            default:
                throw new ServerException(ErrorCode.FORBIDDEN);
        }

        // 更新图书库存
        boolean updated = bookService.updateById(book);
        if (!updated) {
            throw new ServerException(ErrorCode.FORBIDDEN);
        }

        // 返回更新后的图书信息
        Book savedBook = bookService.getById(id);
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(savedBook, bookVO);
        bookVO.setStatus(savedBook.getStock() > 0 ? "可借" : "已借完");
        if (savedBook.getCreateTime() != null) {
            bookVO.setCreateTime(savedBook.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (savedBook.getUpdateTime() != null) {
            bookVO.setUpdateTime(savedBook.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return Result.ok(bookVO);
    }


    /**
     * 按照id查询单本图书
     */
    @GetMapping("/{id}")
    public Result<BookVO> getBookById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        bookVO.setStatus(book.getStock() > 0 ? "可借" : "已借完");
        if (book.getCreateTime() != null) {
            bookVO.setCreateTime(book.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (book.getUpdateTime() != null) {
            bookVO.setUpdateTime(book.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return Result.ok(bookVO);
    }

    /**
     * 判断ISBN是否存在
     */
    @GetMapping("/exists/isbn/{isbn}")
    public HashMap<String, Boolean> existsByIsbn(@PathVariable String isbn) {
        boolean result = bookService.exists(new QueryWrapper<Book>().eq("isbn", isbn));
        HashMap<String, Boolean> exists = new HashMap<>();
        exists.put("exists", result);
        return  exists;
    }

    // @GetMapping("/page")


    /**
     * 分页查询条件查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    public IPage<BookVO> getBooksByPage(BookPageQuery query) {
        Page<Book> page = new Page<>(query.getPage(), query.getSize());
        // 2. 构建查询条件
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        // 标题模糊查询
        if (query.getTitle() != null && !query.getTitle().trim().isEmpty()) {
            wrapper.like("title", query.getTitle());
        }

        // 作者模糊查询
        if (query.getAuthor() != null && !query.getAuthor().trim().isEmpty()) {
            wrapper.like("author", query.getAuthor());
        }
        // 排除已逻辑删除的记录（deleted = 0）
        wrapper.eq("deleted", 0);

        if (query.getSortField() != null && !query.getSortField().trim().isEmpty()) {
            boolean isAsc = "asc".equalsIgnoreCase(query.getSortOrder());
            // 使用数据库字段名自动映射（如 createTime -> create_time）
            if (isAsc) {
                wrapper.orderByAsc(query.getSortField());
            } else {
                wrapper.orderByDesc(query.getSortField());
            }
        } else {
            // 默认排序：按创建时间降序
            wrapper.orderByDesc("create_time");
        }

        IPage<Book> result = bookService.page(page, wrapper);

        return result.convert(book -> {
            BookVO vo = new BookVO();
            BeanUtils.copyProperties(book, vo);
            vo.setStatus(book.getStock() > 0 ? "可借" : "已借完");
            if (book.getCreateTime() != null) {
                vo.setCreateTime(book.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            if (book.getUpdateTime() != null) {
                vo.setUpdateTime(book.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            return vo;
        });
    }


    /**
     * 逻辑删除图书
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        boolean success = bookService.removeById(id);
        if (!success) {
            throw new ServerException(ErrorCode.DELETE_BOOK);
        }
    }

    /**
     * 逻辑恢复已删除图书
     * @param id
     */
    @PutMapping("/restore/{id}")
    public Result<BookVO> restoreBook(@PathVariable Long id) {
        int rows = bookService.restore(id);
        if (rows == 0) {
            throw new ServerException(ErrorCode.UPDATE_BOOK);
        }
        //  再次调用查询单个
        Book bookOne= bookService.getById(id);
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(bookOne, bookVO);
        bookVO.setStatus(bookOne.getStock() > 0 ? "可借" : "已借完");
        if (bookOne.getCreateTime() != null) {
            bookVO.setCreateTime(bookOne.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (bookOne.getUpdateTime() != null) {
            bookVO.setUpdateTime(bookOne.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return Result.ok(bookVO);
    }
}
