package dev.greatseo.portfolio.api.posts.service.impl;

import dev.greatseo.portfolio.api.posts.domain.repository.CommentRepository;
import dev.greatseo.portfolio.api.posts.domain.repository.PostsRepository;
import dev.greatseo.portfolio.api.posts.dto.RegistPostsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PostsServiceImplementTest {

    PostsRepository postsRepository = mock(PostsRepository.class);
    CommentRepository commentRepository = mock(CommentRepository.class);
    ModelMapper modelMapper = mock(ModelMapper.class);

    @BeforeEach
    void setUp() {
    }

    @Test
    void 게시글_등록하기() {
        RegistPostsDto regPosts = new RegistPostsDto("작성자", "테스트 제목", "테스트 본문");
        postsRepository.save(regPosts.toEntity());
    }

    @Test
    void getPosts() {
    }

    @Test
    void getPostsById() {
    }

    @Test
    void setPosts() {
    }

    @Test
    void delPosts() {
    }

    @Test
    void regCommentByPosts() {
    }

    @Test
    void regReplyByComment() {
    }
}