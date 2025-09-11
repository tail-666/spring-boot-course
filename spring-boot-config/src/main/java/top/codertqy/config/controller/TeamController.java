package top.codertqy.config.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.codertqy.config.model.Team;

/**
 * @Author: 27258
 * @Date: 2025/9/11
 */
@RestController
public class TeamController {
    @PostMapping("/team")
    public ResponseEntity<Team> addTeam(@Valid @RequestBody Team team) {
        /**
         * 验证成功返回team
         */
        return ResponseEntity.ok( team);
    }
}
