package top.codertqy.config.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 27258
 * @Date: 2025/9/11
 */
public interface OssService {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public String uploadFile(MultipartFile file);
}
