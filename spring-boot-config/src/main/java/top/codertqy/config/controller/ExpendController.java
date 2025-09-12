package top.codertqy.config.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */

@RestController
@RequestMapping("/captcha")
public class ExpendController {
    /**
     * 获取线性干扰字母验证码图片
     *
     * @return
     */
    @GetMapping("/getcpt1")
    public ResponseEntity<byte[]> getCaptcha1() {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        // 创建一个字节数组输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        lineCaptcha.write(outputStream);

        // 将字节数组输出流转换为字节数组
        byte[] bytes = outputStream.toByteArray();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        // 返回带有图片数据和相应头部的响应实体
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    /**
     * 自定义验证码
     * @return
     */
    @GetMapping("/getcpt2")
    public ResponseEntity<byte[]> getCaptcha2() {
        // 定义图形验证码的长和宽
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        // 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator());

        // 创建一个字节数组输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        captcha.write(outputStream);

        // 将字节数组输出流转换为字节数组
        byte[] bytes = outputStream.toByteArray();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        // 返回带有图片数据和相应头部的响应实体
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    /**
     * 中文分词
     * @param info 分词信息
     * @return
     */
    @GetMapping("/parse")
    public ResponseEntity<String> parseText(String info){
        //自动根据用户引入的分词库的jar来自动选择使用的引擎
        TokenizerEngine engine = TokenizerUtil.createEngine();

        Result result = engine.parse(info);
        //输出：这 两个 方法 的 区别 在于 返回 值
        String join = CollUtil.join((Iterator<Word>) result, " ");
        return  new ResponseEntity<String>(join, HttpStatus.OK);
    }

    /**
     * 哈希单向加密
     * @param password 加密的信息
     * @return
     */
    @GetMapping("/close")
    public ResponseEntity<Map<String, String>> getText(String password){
        HashMap<String, String> hm = new HashMap<>();
        // MD5 加密
        String md5 = DigestUtil.md5Hex(password);
        hm.put("经过md5加密后的", md5);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }

    /**
     * 生成无横线 UUID
     * 例如：e45f1a2b3c4d5e6f7g8h9i0j1k2l3m4n
     */
    @GetMapping("/short-uuid")
    public ResponseEntity<Map<String, String>> generateShortUUID() {
        // 生成无横线的 UUID（32位）
        String fastUUID = IdUtil.fastSimpleUUID();

        Map<String, String> result = new HashMap<>();
        result.put("短_UUID", fastUUID);
        result.put("长度", String.valueOf(fastUUID.length()));
        result.put("描述：", "32位无横线UUID，适合用作唯一标识符");

        return ResponseEntity.ok(result);
    }
}
