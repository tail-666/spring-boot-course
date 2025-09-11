package top.codertqy.config.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ Author: 27258
 * @ Date: 2025/9/11
 */
@Data
@ConfigurationProperties(prefix = "aliyun-oss")
// 指定使用该配置文件
@PropertySource("classpath:aliyun-oss.properties")
// 标注是一个配置类
@Configuration
public class OssConfig {
    private String endpoint;
    private String bucketName;
    private String dir;
    private String accessKey;
    private String secretKey;

}
