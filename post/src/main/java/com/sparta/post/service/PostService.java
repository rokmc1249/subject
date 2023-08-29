package com.sparta.post.service;

import com.sparta.post.dto.RequestDto;
import com.sparta.post.dto.ResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository =postRepository;
    }

    public ResponseDto createPost(RequestDto requestDto){
        Post post = new Post(requestDto);

        Post savePost = postRepository.save(post);



        return new ResponseDto(savePost);
    }

    public List<ResponseDto> getPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(ResponseDto::new).toList();

    }

    public ResponseDto searchPosts(Long id) {
        Post post = findPost(id);

        return new ResponseDto(post);
    }

    @Transactional
    public ResponseDto updatePosts(Long id, RequestDto requestDto) {
        Post post =findPost(id);
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            return new ResponseDto(post);
        }else {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
    }
    public boolean deletePost(Long id,RequestDto requestDto) {

        Post post = findPost(id);
        if (post.getPassword().equals(requestDto.getPassword())) {
            postRepository.delete(post);
            return true;
        }else{
            return false;
        }

    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 포스트는 존재하지 않습니다.")
        );
    }



}
