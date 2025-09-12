package top.codertqy.config.service.impl;

import enums.ResultStatus;
import org.springframework.web.multipart.MultipartFile;
import top.codertqy.config.model.Mail;
import top.codertqy.config.service.MailService;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */
public class MailServiceImpl implements MailService {
    /**
     * 发送简单邮件
     * @param mail
     * @return
     */
    @Override
    public ResultStatus sendSimpleMail(Mail mail) {
        return null;
    }

    /**
     * 发送HTML邮件
     * @param mail
     * @return
     */
    @Override
    public ResultStatus sendHtmlMail(Mail mail) {
        return null;
    }

    /**
     * 发送带附件的邮件,多个邮件
     * @param mail
     * @param files
     * @return
     */
    @Override
    public ResultStatus sendAttachmentsMail(Mail mail, MultipartFile[] files) {
        return null;
    }
}
