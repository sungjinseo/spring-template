package dev.greatseo.portfolio.api.member.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dev.greatseo.portfolio.api.member.domain.entity.Members;
import dev.greatseo.portfolio.api.member.domain.repository.MemberRepository;
import dev.greatseo.portfolio.api.member.dto.MemberResultDto;
import dev.greatseo.portfolio.exception.custom.ApiOtherException;
import lombok.RequiredArgsConstructor;

@Service("memberService")
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final ModelMapper modelMapper;

	public MemberResultDto getMemberById(Long id) {
		
		Members entity = memberRepository.findById(id)
				.orElseThrow(() -> new ApiOtherException("Member Not Found"));

		return modelMapper.map(entity, MemberResultDto.class);
	}
	
	

}
