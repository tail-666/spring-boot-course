package top.codertqy.config.service.imple;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.codertqy.config.config.OssConfig;
import top.codertqy.config.service.OssService;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: 27258
 * @Date: 2025/9/11
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Resource
    private OssConfig ossConfig;

    /**
     * 上传文件 逻辑处理
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file) {
        if (file != null) {
            // 获取原始文件
            String originalFilename = file.getOriginalFilename();
            // 获取文件后缀
            assert originalFilename != null;
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 生成新的文件名
            String newFileName = UUID.randomUUID() + suffix;
            log.info("新文件名：{}", newFileName);
            // 读取配置文件中的配置值
            String endpoint = ossConfig.getEndpoint();
            String bucketName = ossConfig.getBucketName();
            String accessKey = ossConfig.getAccessKey();
            String secretKey = ossConfig.getSecretKey();
            String dir = ossConfig.getDir();
            // 创建OSS客户端
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
            // 创建文件元信息,设置文件类型为jpg,默认为application/octet-stream（二进制流）浏览器打开会下载而非预览
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");
            // 拼接最终文件在服务器的路径如： img/xxx.jpg
            String uploadPath = dir + newFileName;
            InputStream inputStream;
            try {
                // 获取文件输入流
                inputStream = file.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 使用putObject方法上传，指明文件的存储空间名称、文件名称、文件输入流，以及文件元信息
            ossClient.putObject(bucketName, uploadPath, inputStream, meta);
            // 关闭OSS客户端
            ossClient.shutdown();
            // 返回文件访问路径
            return "https://" + bucketName + "." + endpoint + "/" + uploadPath;

        }

        return "上传失败";
    }
}
