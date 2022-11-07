package dev.greatseo.portfolio.api.posts.domain.repository;

import dev.greatseo.portfolio.api.member.domain.entity.Members;
import dev.greatseo.portfolio.api.posts.domain.entity.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @Test
    @DisplayName("책한권등록 테스트")
    public void 게시글_등록_test(){
        // given
        String title = "테스트북";
        String author = "beginner";

        Posts posts = Posts.builder()
                .title(title)
                .content("내용")
                .author("aaa")
                .build();

        //when
        Posts bookPS = postsRepository.save(posts);

        //then
        assertEquals(title, bookPS.getTitle());
    }
}
