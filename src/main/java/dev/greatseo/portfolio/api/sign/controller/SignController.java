package dev.greatseo.portfolio.api.sign.controller;

import javax.validation.Valid;

import dev.greatseo.portfolio.api.sign.dto.AuthenticationDto;
import dev.greatseo.portfolio.api.sign.dto.JoinDto;
import dev.greatseo.portfolio.api.sign.dto.LoginDto;
import dev.greatseo.portfolio.api.sign.service.SignService;
import dev.greatseo.portfolio.util.auth.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SignController {

	private final SignService apiSignService;
	
	private final AuthProvider authProvider;

	/**
	 * @method 설명 : 회원가입
	 * @param joinDto
	 * @throws Exception
	 */
	@PostMapping(value = {"/signup"})
	public ResponseEntity<Boolean> appJoin(@Valid @RequestBody JoinDto joinDto) throws Exception {

		return ResponseEntity.ok()
				.body(apiSignService.regMember(joinDto));
	}
	
	/**
	 * @method 설명 : 로그인
	 * @param loginDto
	 * @throws Exception
	 */
	@PostMapping(value = {"/signin"})
	public ResponseEntity<AuthenticationDto> appLogin(@Valid @RequestBody LoginDto loginDto) throws Exception {

		AuthenticationDto authentication = apiSignService.loginMember(loginDto);

		return ResponseEntity.ok()
				.header("accesstoken", authProvider
						.createToken(
							authentication.getId(),
							authentication.getEmail(),
							"USER"))
				.body(authentication);
	}
}
