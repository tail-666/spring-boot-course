package top.codertqy.config.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TeamTest {
    @Resource
    private Team team;
    @Test
    void testTeamLeader() {
       log.info("addTeam: {}",team);
        Assertions.assertEquals("codertqy",team.getLeader());
    }
    @Test
    void testTeamAge() {
        Assertions.assertEquals(1,team.getAge());
    }
}