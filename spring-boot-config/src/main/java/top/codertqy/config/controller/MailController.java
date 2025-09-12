package top.codertqy.config.controller;

import enums.ResultStatus;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.codertqy.config.model.Mail;
import top.codertqy.config.pojo.ApiResponse;
import top.codertqy.config.service.MailService;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */

@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;

    /**
     * 发送简单邮件
     *
     * @param mail
     * @return
     */
    @PostMapping("/simple")
    public ResponseEntity<ApiResponse<ResultStatus>> sendSimpleMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs = mailService.sendSimpleMail(mail);
        if (rs == ResultStatus.SUCCESS) {
            return ResponseEntity.ok(ApiResponse.success("发送成功", rs));
        }
        // 发送失败，返回400
        return ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }

    /**
     * 发送HTML邮件
     *
     * @param mail
     * @return
     */
    @PostMapping("/html")
    public ResponseEntity<ApiResponse<ResultStatus>> sendHtmlMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs = mailService.sendHtmlMail(mail);
        return rs == ResultStatus.SUCCESS ? ResponseEntity.ok(ApiResponse.success("发送成功", rs)) : ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }

    /**
     * 发送带附件的邮件
     *
     * @param to,subject,content 邮件信息
     * @param files 附件文件
     * @return 邮件发送结果
     */
    @PostMapping(value="/attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ResultStatus>> sendAttachmentsMail(
            @Valid  @RequestPart("to") String to,
            @Valid  @RequestPart("subject") String subject,
            @Valid  @RequestPart("content") String content,
            @RequestPart("files") MultipartFile[] files
    ) {
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setContent(content);
        ResultStatus rs = mailService.sendAttachmentsMail(mail, files);
        return rs == ResultStatus.SUCCESS ? ResponseEntity.ok(ApiResponse.success("发送成功", rs)) : ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }
}
