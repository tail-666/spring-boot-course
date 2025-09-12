package top.codertqy.config.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */
@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @Value("${custom.coder.content}")
    private String content;

    /**
     * 获取二维码
     * @return
     */
    @GetMapping("/generate")
    public ResponseEntity<byte[]> getQrCode() {
        // TODO: 2025/9/12 生成二维码
        // 创建一个字节数组输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate(content, 300, 300, ImgUtil.IMAGE_TYPE_PNG, outputStream);
        byte[] bytes = outputStream.toByteArray();
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
    /**
     * 根据动态参数获取二维码
     * @param text
     * @return
     */
    @GetMapping("/generate/{text}")
    public ResponseEntity<byte[]> getQrCode(@PathVariable String text) {
        // TODO: 2025/9/12 生成二维码
        // 创建一个字节数组输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate(text, 300, 300, ImgUtil.IMAGE_TYPE_PNG, outputStream);
        byte[] bytes = outputStream.toByteArray();
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
