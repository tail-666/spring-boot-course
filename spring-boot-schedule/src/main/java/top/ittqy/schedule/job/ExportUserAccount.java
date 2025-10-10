package top.ittqy.schedule.job;

import com.alibaba.excel.EasyExcel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import top.ittqy.schedule.entity.UserAccount;
import top.ittqy.schedule.mapper.UserAccountMapper;

import java.util.List;

/**
 * @Author: 27258
 * @Date: 2025/10/10
 */

@Slf4j
@AllArgsConstructor
public class ExportUserAccount  extends QuartzJobBean {
    private final UserAccountMapper userAccountMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("执行导出任务");
        List<UserAccount> list = userAccountMapper.selectList(null);
        String fileName = "./用户数据.xlsx";
        EasyExcel.write(fileName, UserAccount.class).sheet("用户数据").doWrite(() -> list);
    }
}
