package dev.greatseo.portfolio.api.sign.service;

import dev.greatseo.portfolio.api.sign.dto.AuthenticationDto;
import dev.greatseo.portfolio.api.sign.dto.JoinDto;
import dev.greatseo.portfolio.api.sign.dto.LoginDto;

public interface SignService {

	public Boolean regMember(JoinDto joinDto);
	public AuthenticationDto loginMember(LoginDto loginDto);

}
