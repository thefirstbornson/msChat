package vtb.app.adapter.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import vtb.app.domain.UserData;
import vtb.app.port.in.TokenService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers  = TokenController.class)
class TokenControllerTest {
    public static final String URL_TEMPLATE = "/api/user/sendchattoken";
    String TOKEN = "b2ac2c29-b203-4a04-9136-c1c65753423d";
    String EXPECTED_REPLY = "{\"firstName\":\"q\",\"lastName\":\"qq\",\"patronymic\":\"qqq\",\"login\":\"login\",\"bkoId\":\"id123\",\"token\":null,\"client\":null}";
    String JWT = "Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0YW10YW1AdnRiLnJ1IiwiaXNzIjoiaHR0cHM6Ly9wY" +
            "XNzcG9ydC52dGIucnUvcGFzc3BvcnQiLCJleHAiOiIxNTk2NjQ3Njg5IiwiaWF0IjoiMTU5NjY0NzE5MCIsImp0aSI6IjVlMzRnaDU" +
            "2Nzg5MHR5NzhubWtsNyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiXSwidHJ1c3QiOiJmYWxzZSIsIm5vbmNlIjoiNTQ2NDY0Z" +
            "GZzNWFmNHM2ZjU0NjQ2NCIsInJlYWxtIjoiIiwiY2hhbm5lbCI6ImFwaWMiLCJjdHhpIjoiMTIzZTQ1NjctZTg5Yi0xMmQzLWE0NTY" +
            "tNDI2NjU1NDQwMDAwIiwibWV0aG9kIjoibG9naW4iLCJzZmFjdG9yIjpbIm90cCIsInNtcyIsImJpbyJdfQ.jm2xNlqyO3x_u3W3MO" +
            "v20BtVaNOYrAmf7h2rGS5F6oUDWGwAJcCz9x0_5yURYUYD3TN8MopnmOUsH3dewvnfHA";
    String JWT_WITHOUT_CTXI = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0YW10YW1AdnRiLnJ1IiwiaXNzIjoi" +
            "aHR0cHM6Ly9wYXNzcG9ydC52dGIucnUvcGFzc3BvcnQiLCJleHAiOiIxNTk2NjQ3Njg5IiwiaWF0IjoiMTU5NjY0NzE5MCIsImp0aS" +
            "I6IjVlMzRnaDU2Nzg5MHR5NzhubWtsNyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiXSwidHJ1c3QiOiJmYWxzZSIsIm5vbmNl" +
            "IjoiNTQ2NDY0ZGZzNWFmNHM2ZjU0NjQ2NCIsInJlYWxtIjoiIiwiY2hhbm5lbCI6ImFwaWMiLCJtZXRob2QiOiJsb2dpbiIsInNmYW" +
            "N0b3IiOlsib3RwIiwic21zIiwiYmlvIl19.v8Kkt0FySQMWi-ZfaLXalfer8MZpXRPQZGpAnlASxHk";
    String JWT_WITH_INVALID_PAYLOAD = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.QVNERkFTREZBRFNGYXNmc2FkZmFzZGYz" +
            "NTQzMTUzMjQyMTQ=.v8Kkt0FySQMWi-ZfaLXalfer8MZpXRPQZGpAnlASxHk";
    private UserData userData;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    void setUp(){
        userData = UserData.builder()
                .firstName("q")
                .lastName("qq")
                .patronymic("qqq")
                .login("login")
                .bkoId("id123")
                .build();
    }

    @Test
    @DisplayName("Возврат значения id сессия по jwt")
    void sendToken() throws Exception {
        given(tokenService.getUserData(anyString())).willReturn(userData);
        mockMvc.perform(post(URL_TEMPLATE)
                .param("token", TOKEN)
                .header("Authorization", JWT))
                .andExpect(content().string(EXPECTED_REPLY))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Возвращает статус ошибки 400 при отсутствии параметра token")
    void sendTokenWithoutToken() throws Exception {
        mockMvc.perform(post(URL_TEMPLATE)
                .header("Authorization", JWT))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Возвращает статус ошибки 400 при отсутствии хэдера Authorisation")
    void sendTokenWithoutHeader() throws Exception {
        mockMvc.perform(post(URL_TEMPLATE)
                .param("token", TOKEN))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @DisplayName("Возвращает статус ошибки 500 при отсутствии поля ctxi в payload jwt")
    void sendTokenWithoutCtxi() throws Exception {
        mockMvc.perform(post(URL_TEMPLATE)
                .param("token", TOKEN)
                .header("Authorization", JWT_WITHOUT_CTXI))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("Возвращает статус ошибки 500 при некоррекном payload jwt (не json)")
    void sendTokenWithInvalidJWT() throws Exception {
        mockMvc.perform(post(URL_TEMPLATE)
                .param("token", TOKEN)
                .header("Authorization", JWT_WITH_INVALID_PAYLOAD))
                .andExpect(status().is5xxServerError());
    }
}