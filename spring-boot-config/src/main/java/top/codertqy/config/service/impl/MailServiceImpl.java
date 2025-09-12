package top.codertqy.config.service.impl;

import enums.ResultStatus;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.codertqy.config.model.Mail;
import top.codertqy.config.service.MailService;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */
@Service
public class MailServiceImpl implements MailService {
    // 读取配置的发件人
    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender javaMailSender;
    /**
     * 发送简单邮件
     * @param mail
     * @return
     */
    @Override
    public ResultStatus sendSimpleMail(Mail mail) {
        // 发送简单文件，根据发送情况返回不同信息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        try {
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        } catch (Exception e) {
            return ResultStatus.FAIL;
        }
    }

    /**
     * 发送HTML邮件
     * @param mail
     * @return
     */
    @Override
    public ResultStatus sendHtmlMail(Mail mail) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, StandardCharsets.UTF_8.name());
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            // 第二个参数为true表示使用HTML格式
            helper.setText(mail.getContent(), true);
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        } catch (MessagingException e) {
            return ResultStatus.FAIL;
        }
    }

    /**
     * 发送带附件的邮件,多个邮件
     * @param mail
     * @param files
     * @return
     */
    @Override
    public ResultStatus sendAttachmentsMail(Mail mail, MultipartFile[] files) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            // 支持富文本正文
            helper.setText(mail.getContent(),true);
            if(files!=null){
                for (MultipartFile file : files) {
                    if(file!=null&&!file.isEmpty()){
                        helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()),new ByteArrayResource(file.getBytes()));
                    }
                }
            }
            // 发送
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        } catch (Exception e) {
            return ResultStatus.FAIL;
        }
    }
}
