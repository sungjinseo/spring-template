package dev.greatseo.portfolio.api.posts.domain.repository;

import dev.greatseo.portfolio.api.posts.domain.repository.support.CustomCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.greatseo.portfolio.api.posts.domain.entity.PostsComment;

import java.util.List;

public interface CommentRepository extends JpaRepository<PostsComment, Long> , CustomCommentRepository {

//	List<Comment> findByPostsId(Long id);

	
}
