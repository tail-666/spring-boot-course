package top.codertqy.config.service;

import enums.ResultStatus;
import org.springframework.web.multipart.MultipartFile;
import top.codertqy.config.model.Mail;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */

public interface MailService {
    ResultStatus sendSimpleMail(Mail mail);
    ResultStatus sendHtmlMail(Mail mail);
    ResultStatus sendAttachmentsMail(Mail mail, MultipartFile[] files);
}
