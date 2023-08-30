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
        Post post = new Post(requestDto); // 객체 -> entity

        Post savePost = postRepository.save(post); // DB에 저장



        return new ResponseDto(savePost); // DB에 저장 하고자 한 내용 반환
    }

    public List<ResponseDto> getPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(ResponseDto::new).toList(); // 수정한 날짜 기준 내림차순 전체 조회

    }

    public ResponseDto searchPosts(Long id) {
        Post post = findPost(id); // id값 찾는 메서드

        return new ResponseDto(post); // 해당 id값의 entity 반환
    }

    @Transactional
    public ResponseDto updatePosts(Long id, RequestDto requestDto) {
        Post post =findPost(id); // id값 조회하는 메서드

        // DB에서 id값으로 조회한 해당 객체의 비밀번호와 클라이언트에서 요청받은 비밀번호와 일치하는지 확인
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto); // 일치하면 해당 id값의 entity 변경내용 수정
            return new ResponseDto(post); // 수정된 객체 반환
        }else {
            throw new IllegalArgumentException("비밀번호 불일치"); // 비밀번호 틀릴 시 예외처리
        }
    }
    public boolean deletePost(Long id,RequestDto requestDto) {

        Post post = findPost(id); // id값 조회하는 메서드
        if (post.getPassword().equals(requestDto.getPassword())) { // DB 부여받은 비밀번호 -클라이언트에서 부여받은 비밀번호 일치여부
            postRepository.delete(post); // 일치 시 삭제
            return true;
        }else{
            return false;
        }

    }

    private Post findPost(Long id) { // DB에서 ID값 조회 후 없으면 예외처리
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 포스트는 존재하지 않습니다.")
        );
    }



}
