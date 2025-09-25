package top.codertqy.boot.mp.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.codertqy.boot.mp.entity.UserAccount;
import java.util.List;
@SpringBootTest
@Slf4j
class UserAccountMapperTest {
    @Resource
    private UserAccountMapper userAccountMapper;
    @Test
    void testSelectAll(){
        List<UserAccount> userAccounts = userAccountMapper.selectList(null);
        log.info("查询所有用户结果：{}",userAccounts);
    }
    @Test
    void testSelectOne(){
        UserAccount userAccount = userAccountMapper.selectById(1);
        log.info("根据id查询用户结果：{}",userAccount);
    }
}