package com.sparta.post.controller;

import com.sparta.post.dto.RequestDto;
import com.sparta.post.dto.ResponseDto;
import com.sparta.post.service.PostService;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    public PostController (PostService postService){
        this.postService=postService;
    }
    @PostMapping("/post") // 생성
    public ResponseEntity<ResponseDto> createPost(@RequestBody RequestDto requestDto){
        ResponseDto responseDto = postService.createPost(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED); // responseDto, 상태코드 반환
    }

    @GetMapping("/posts") // 전체 조회
    public ResponseEntity<List<ResponseDto>> getPosts(){
        List<ResponseDto> responseDto = postService.getPosts();
        return new ResponseEntity<>(responseDto, HttpStatus.OK); // responseDto, 상태코드 반환
    }

    @GetMapping("/post/{id}") // 식별자를 통한 조회
    public ResponseEntity<ResponseDto> searchPosts(@PathVariable Long id) {
        ResponseDto responseDto = postService.searchPosts(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK); // responseDto, 상태코드 반환
    }

    @PutMapping("post/{id}") // 수정
    public ResponseEntity<ResponseDto> updatePosts(@PathVariable Long id,@RequestBody RequestDto requestDto){ // 요청받은 id와 requestDto
        ResponseDto  responseDto = postService.updatePosts(id,requestDto);
        return  new ResponseEntity<>(responseDto,HttpStatus.OK); // responseDto, 상태코드 반환
    }

    @DeleteMapping("post/{id}") // 삭제 시
    public String deletePosts(@PathVariable Long id, @RequestBody RequestDto requestDto) { //요청받은 id와 requestDto
        boolean result = postService.deletePost(id, requestDto); // 비밀번호 일치 여부
        if(result){
            return "{\"success\": " + result + "}"; // 참 {success:true}
        }
        return "{\"success\": " + result + "}";  // 거짓 {success:false}


    }

}
