package top.ittqy.schedule.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 27258
 * @Date: 2025/10/10
 */
@Configuration
@ConfigurationProperties(prefix = "db.backup")
@Data
public class DbConfig {
    private String host;
    private String port;
    private String username;
    private String password;
    private String dbName;
    private String localPath;
}
