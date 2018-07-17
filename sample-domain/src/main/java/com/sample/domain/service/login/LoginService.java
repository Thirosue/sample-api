package com.sample.domain.service.login;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sample.domain.dto.system.MailTemplate;
import com.sample.domain.dto.system.Staff;
import com.sample.domain.exception.NoDataFoundException;
import com.sample.domain.base.SendMailHelper;
import com.sample.domain.repository.system.MailTemplateRepository;
import com.sample.domain.repository.system.StaffRepository;
import com.sample.domain.service.BaseTransactionalService;

import lombok.val;

/**
 * ログインサービス
 */
@Service
public class LoginService extends BaseTransactionalService {

    @Value("${spring.mail.properties.mail.from}")
    String fromAddress;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    MailTemplateRepository mailTemplateRepository;

    @Autowired
    SendMailHelper sendMailHelper;

    /**
     * パスワードリセットメールを送信します。
     * 
     * @param email
     * @param url
     */
    public void sendResetPasswordMail(String email, String url) {
        Assert.notNull(fromAddress, "fromAddress must be set.");

        val where = new Staff();
        where.setEmail(email);
        val staff = staffRepository.findOne(where);

        staff.ifPresent(s -> {
            // トークンを発行する
            val token = UUID.randomUUID().toString();
            s.setPasswordResetToken(token);
            s.setTokenExpiresAt(LocalDateTime.now().plusHours(2)); // 2時間後に失効させる
            staffRepository.update(s);

            // メールを作成する
            val mailTemplate = getMailTemplate("passwordReset");
            val subject = mailTemplate.getSubject();
            val templateBody = mailTemplate.getTemplateBody();

            Map<String, Object> objects = new HashMap<>();
            objects.put("staff", s);
            objects.put("url", StringUtils.join(url, "?token=", token));

            // テンプレートエンジンにかける
            val body = sendMailHelper.getMailBody(templateBody, objects);

            // メールを送信する
            sendMailHelper.sendMail(fromAddress, new String[] { s.getEmail() }, subject, body);
        });
    }

    /**
     * トークンの有効性をチェックします。
     * 
     * @param token
     * @return
     */
    public boolean isValidPasswordResetToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }

        // トークンの一致と有効期限をチェックする
        val where = new Staff();
        where.setPasswordResetToken(token);
        val staff = staffRepository.findOne(where);

        if (!staff.isPresent()) {
            return false;
        }

        return true;
    }

    /**
     * パスワードを更新します。
     * 
     * @param token
     * @param password
     * @return
     */
    public boolean updatePassword(String token, String password) {
        // トークンの一致と有効期限をチェックする
        val where = new Staff();
        where.setPasswordResetToken(token);
        val staff = staffRepository.findOne(where);

        if (!staff.isPresent()) {
            return false;
        }

        staff.ifPresent(s -> {
            // パスワードをリセットする
            s.setPasswordResetToken(null);
            s.setTokenExpiresAt(null);
            s.setPassword(password);
            staffRepository.update(s);
        });

        return true;
    }

    /**
     * メールテンプレートを取得する。
     *
     * @return
     */
    protected MailTemplate getMailTemplate(String templateKey) {
        val where = new MailTemplate();
        where.setTemplateKey(templateKey);
        return mailTemplateRepository.findOne(where).orElseThrow(
                () -> new NoDataFoundException("templateKey=" + where.getTemplateKey() + " のデータが見つかりません。"));
    }
}
