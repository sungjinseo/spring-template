package dev.greatseo.portfolio.api.posts.domain.repository.support;


import java.util.List;

import dev.greatseo.portfolio.api.posts.domain.entity.PostsComment;

public interface CustomCommentRepository {

    public List<PostsComment> findByPostsId(Long postsId);

}
