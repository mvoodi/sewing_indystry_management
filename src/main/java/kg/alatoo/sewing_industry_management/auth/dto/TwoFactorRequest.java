package kg.alatoo.sewing_industry_management.auth.dto;

import lombok.*;

/* TwoFactorRequest.java */
public record TwoFactorRequest(String email, int code) {}

