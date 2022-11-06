package dev.greatseo.portfolio.api.posts.service;

import dev.greatseo.portfolio.api.posts.dto.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public interface PostsService {
	
    public Long regPosts(RegistPostsDto regPosts);

    public PageImpl<PostsResDto> getPosts(PageRequest pageble);

    public PostsResDto getPostsById(Long id);

    public void setPosts(ModifyPostsDto setPosts);

	public void delPosts(Long id);

	public void regCommentByPosts(RegistCommentDto regComment);

    public void regReplyByComment(RegistReplyDto regReply);
}
