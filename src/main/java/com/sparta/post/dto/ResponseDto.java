package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String password;


    public ResponseDto(Post post) {
        this.createdAt= post.getCreatedAt();
        this.modifiedAt=post.getModifiedAt();
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.password= post.getPassword();
    }
}
