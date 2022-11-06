package dev.greatseo.portfolio;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.greatseo.portfolio.api.posts.dto.RegistPostsDto;
import dev.greatseo.portfolio.api.posts.dto.ModifyPostsDto;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class PortfolioApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeAll
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    void contextLoads_게시판_글_목록조회() throws Exception{

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("page", "1");

        mockMvc.perform(get("/api/v1/posts").params(param))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void contextLoads_게시판_글_상세조회() throws Exception {

        mockMvc.perform(get("/api/v1/posts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void contextLoads_게시판_글_등록() throws Exception {

        RegistPostsDto posts = new RegistPostsDto("작성자", "테스트 제목", "테스트 본문");
        String content = objectMapper.writeValueAsString(posts);

        mockMvc.perform(post("/api/v1/posts")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    void contextLoads_게시판_글_수정() throws Exception {

        ModifyPostsDto posts = new ModifyPostsDto((long)1, "수정자", "제목 수정 테스트", "본문 수정 테스트");
        String content = objectMapper.writeValueAsString(posts);

        mockMvc.perform(put("/api/v1/posts/1")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void contextLoads_게시판_글_삭제() throws Exception {

        mockMvc.perform(delete("/api/v1/posts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
