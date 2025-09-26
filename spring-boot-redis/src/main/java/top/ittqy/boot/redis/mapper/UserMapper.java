package top.ittqy.boot.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.ittqy.boot.redis.entity.User;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 * @Description: 用户相关操作
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
