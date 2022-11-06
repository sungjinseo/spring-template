package dev.greatseo.portfolio.api.member.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResultDto {
	
	private Long id;
	
	private String email;
	
	private String name;
	
	private String mobile;
	
	private String nickname;
	
	private String profile;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
	
}
