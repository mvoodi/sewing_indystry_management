package kg.alatoo.sewing_industry_management.auth.service;

public interface EmailService {
    void sendVerifyEmail(String to, String message);
}
