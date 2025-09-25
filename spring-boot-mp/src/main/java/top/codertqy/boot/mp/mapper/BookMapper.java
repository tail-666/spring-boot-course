package top.codertqy.boot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import top.codertqy.boot.mp.entity.Book;

/**
 * @Author: 27258
 * @Date: 2025/9/20
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    /**
     * 恢复被逻辑删除的图书
     */
    @Update("update book set deleted = 0 where id = #{id} and deleted=1")
    Integer restore(Long id);
}
