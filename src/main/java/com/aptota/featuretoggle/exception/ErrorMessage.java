package com.aptota.featuretoggle.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private int statusCode;
    private String status;
    private String message;
    private LocalDateTime timestamp;
}
