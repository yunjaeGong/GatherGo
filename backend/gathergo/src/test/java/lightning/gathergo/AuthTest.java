package lightning.gathergo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class AuthTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("로그인시 Session 정보 json으로 발급 확인")
    public void login() throws Exception {
        this.mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"userId\": \"asdf\",\n" +
                                "\"password\": \"asdf\"\n" +
                                "}")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("sessionId")))
                .andExpect(content().string(containsString("asdf")));
    }
}
