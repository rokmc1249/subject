package com.sparta.post.dto;

import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestDto {
    private String author;
    private String title;
    private String content;
    private String password;


}
