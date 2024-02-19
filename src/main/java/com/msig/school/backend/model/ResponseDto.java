package com.msig.school.backend.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseDto<T> {
    private String message;
    private Integer statusCode;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ResponseDto<T> of(T data, HttpStatus httpStatus, String message){
        return ResponseDto.<T>builder().data(data).message(message).statusCode(httpStatus.value()).timestamp(LocalDateTime.now()).build();
    }
}
