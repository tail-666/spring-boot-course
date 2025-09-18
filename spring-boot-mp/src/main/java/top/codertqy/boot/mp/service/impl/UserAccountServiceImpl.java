package top.codertqy.boot.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.codertqy.boot.mp.mapper.UserAccountMapper;
import top.codertqy.boot.mp.pojo.UserAccount;
import top.codertqy.boot.mp.service.UserAccountService;

/**
 * @Author: 27258
 * @Date: 2025/9/18
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {
//     继承ServieImpl 自动获取所有业务逻辑实现

}
