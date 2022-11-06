package dev.greatseo.portfolio.api.posts.controller;


import dev.greatseo.portfolio.api.posts.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import dev.greatseo.portfolio.api.posts.service.PostsService;
import lombok.RequiredArgsConstructor;

@Tag(name = "posts", description = "게시물 API")
@RestController
@RequestMapping(value = {"api/v1/posts"}, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostsController {




	private final PostsService postsService;

	@Operation(summary = "get posts", description = "게시글 목록조회")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostsResDto.class))),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND"),
			@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@Parameters({
			@Parameter(name = "province", description = "시", example = "경기도"),
			@Parameter(name = "city", description = "도", example = "고양시"),
			@Parameter(name = "hashtag", description = "검색한 해시태그", example = "['#자장면', '#중국집']")
	})
	@ResponseBody
	@GetMapping(value = {""})
	public ResponseEntity<PageImpl<PostsResDto>> getPosts(
			@RequestParam("page") Integer page) {

		return ResponseEntity.ok()
				 			 .body(postsService.getPosts(PageRequest.of(page - 1, 8, Sort.by("id").descending())));
	}

	/**
	 * @method 설명 : 게시글 등록
	 * @param regPosts
	 * @throws Exception
	 */
	@PostMapping(value = {""})
	public ResponseEntity<Long> regPosts(
			@Valid @RequestBody RegistPostsDto regPosts) {

		return ResponseEntity.ok()
							 .body(postsService.regPosts(regPosts));
	}

	/**
	 * @method 설명 : 게시글 상세조회
	 * @param id
	 * @return
	 */
	@GetMapping(value = {"/{id}"})
	public ResponseEntity<PostsResDto> getPostsDetail(
			@PathVariable Long id) {

		return ResponseEntity.ok()
							 .body(postsService.getPostsById(id));
	}

	/**
	 * @method 설명 : 게시글 수정
	 * @param id
	 * @param setPosts
	 */
	@PutMapping(value = {"/{id}"})
	public ResponseEntity<String> setPosts(
			@PathVariable Long id,
			@Valid @RequestBody ModifyPostsDto setPosts) {

		postsService.setPosts(setPosts);

		return ResponseEntity.ok()
							 .body("UPDATE SUCCESS");
	}

	/**
	 * @method 설명 : 게시글 삭제
	 * @param id
	 */
	@DeleteMapping(value = {"/{id}"})
	public ResponseEntity<String> delPosts(
			@PathVariable Long id) {

		postsService.delPosts(id);

		return ResponseEntity.ok()
							 .body("DELETE SUCCESS");
	}

	/**
	 * @method 설명 : 게시글 댓글 등록
	 * @param postsId
	 * @param regComment
	 * @throws Exception
	 */
	@PostMapping(value = {"/{postsId}/comment"})
	public void regCommentByPosts(
			@PathVariable Long postsId,
			@Valid @RequestBody RegistCommentDto regComment) {

		postsService.regCommentByPosts(regComment);
	}

	/**
	 * @method 설명 : 댓글의 답글 등록
	 * @param commentId
	 * @param regReply
	 */
	@PostMapping(value = {"/{postId}/comment/{commentId}"})
	public void regReplyByComment(
			@PathVariable Long postsId,
			@PathVariable Long commentId,
			@Valid @RequestBody RegistReplyDto regReply) {

		postsService.regReplyByComment(regReply);
	}

}
