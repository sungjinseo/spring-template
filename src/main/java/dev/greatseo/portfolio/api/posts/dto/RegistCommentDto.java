package dev.greatseo.portfolio.api.posts.dto;

import dev.greatseo.portfolio.api.member.domain.entity.Members;
import dev.greatseo.portfolio.api.posts.domain.entity.Posts;
import dev.greatseo.portfolio.api.posts.domain.entity.PostsComment;
import dev.greatseo.portfolio.util.auth.AuthUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RegistCommentDto {

	@NotBlank(message = "등록할 댓글이 존재하지 않습니다.")
	private String comment;

	private Long postsId;
	
	public PostsComment toEntity() {
		
		Posts posts = Posts.builder().id(postsId).build();
		Members member = Members.builder().id(AuthUtil.getId()).build();
		
		PostsComment build = PostsComment.builder()
				.comment(comment)
				.depthNo((long) 0)
				.posts(posts)
				.member(member)
				.build();
		
		return build;
	}
	
}
