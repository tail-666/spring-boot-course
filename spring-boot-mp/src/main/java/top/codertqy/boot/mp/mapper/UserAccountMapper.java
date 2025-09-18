package top.codertqy.boot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.codertqy.boot.mp.pojo.UserAccount;

/**
 * @Author: 27258
 * @Date: 2025/9/18
 */
@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {
//     继承BaseMapper 获得基础的CRUD能力
//     也可以在此添加自定义的查询方法
}
