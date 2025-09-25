package top.codertqy.boot.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.codertqy.boot.mp.entity.UserAccount;

import java.util.List;

/**
 * @Author: 27258
 * @Date: 2025/9/18
 */

public interface UserAccountService extends IService<UserAccount> {
//     继承IService获得基础业务逻辑能力
//     可以添加自定义方法

    /**
     * 创建单个用户（自动加密密码）
     * @return
     */
    boolean createUser(UserAccount user);
    /**
     * 创建多个用户（自动加密密码）
     * @return
     */
    boolean createUsers(List<UserAccount> users);
}
