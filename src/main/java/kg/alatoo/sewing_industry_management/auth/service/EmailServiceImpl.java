package kg.alatoo.sewing_industry_management.auth.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendVerifyEmail(String to, String token) {
        String link = "https://your-host/api/auth/verify?token=" + token;
        String html = """
                <p>Здравствуйте!</p>
                <p>Нажмите на ссылку, чтобы подтвердить e-mail:</p>
                <p><a href="%s">%s</a></p>
                <p>Ссылка действительна 24 ч.</p>
                """.formatted(link, link);

        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("Подтвердите e-mail");
            helper.setText(html, true);
            mailSender.send(mail);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to send verification mail", e);
        }
    }
}
