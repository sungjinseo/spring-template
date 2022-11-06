package dev.greatseo.portfolio.api.posts.dto;

import dev.greatseo.portfolio.api.member.domain.entity.Members;
import dev.greatseo.portfolio.api.posts.domain.entity.Posts;
import dev.greatseo.portfolio.util.auth.AuthUtil;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RegistPostsDto {

	@NotBlank(message = "'author' is a required input value")
	private String author;

	@NotBlank(message = "'title' is a required input value")
	private String title;

	@NotBlank(message = "'content' is a required input value")
	private String content;

	public Posts toEntity() {

		Posts build = Posts.builder()
				.author(author)
				.title(title)
				.content(content)
				.build();

		return build;
	}

}