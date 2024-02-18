package com.msig.school.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TokenDto {
    private String token;
    private LocalDateTime generatedAt;
    private LocalDateTime expiredAt;
}
