package top.codertqy.config.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.codertqy.config.service.OssService;

/**
 * @Author: 27258
 * @Date: 2025/9/11
 */
@RestController
public class OssController {
    @Resource
    private OssService ossService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String uploadFile(MultipartFile file){
        return ossService.uploadFile(file);
    }
}
