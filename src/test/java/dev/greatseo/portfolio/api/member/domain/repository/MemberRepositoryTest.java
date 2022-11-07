package dev.greatseo.portfolio.api.member.domain.repository;

import dev.greatseo.portfolio.api.member.domain.entity.Members;
import dev.greatseo.portfolio.api.posts.domain.entity.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest  // DB와 관련된 컴포넌트만 메모리에 로딩
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
    @Autowired  // DI Test시만 autowired 사용 일반적으로 @ArgContsruct
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 등록 테스트")
    public void 멤버_등록_test(){
        // given
        String name = "서성진";
        String email = "sjseo85@gmail.com";
        String nickname = "greatseo";
        String password = "greatseo";

        Members member = Members.builder()
                .name(name)
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();

        //when
        Members memberPS = memberRepository.save(member);

        //then
        assertEquals(name, memberPS.getName());
    }
}