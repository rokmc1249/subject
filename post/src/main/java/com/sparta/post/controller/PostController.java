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
    @PostMapping("/post")
    public ResponseEntity<ResponseDto> createPost(@RequestBody RequestDto requestDto){
        ResponseDto responseDto = postService.createPost(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<ResponseDto>> getPosts(){
        List<ResponseDto> responseDto = postService.getPosts();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<ResponseDto> searchPosts(@PathVariable Long id) {
        ResponseDto responseDto = postService.searchPosts(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("post/{id}")
    public ResponseEntity<ResponseDto> updatePosts(@PathVariable Long id,@RequestBody RequestDto requestDto){
        ResponseDto  responseDto = postService.updatePosts(id,requestDto);
        return  new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("post/{id}")
    public String deletePosts(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        boolean result = postService.deletePost(id, requestDto);
        if(result){
            return "{\"success\": " + result + "}";
        }
        return "{\"success\": " + result + "}";


    }

}
