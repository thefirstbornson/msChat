package vtb.app.adapter.persistence.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import vtb.app.adapter.persistence.web.api.Mapper;
import vtb.app.adapter.persistence.web.exception.SessionDataNotFoundException;
import vtb.app.adapter.persistence.web.exception.SessionDataServerException;
import vtb.app.adapter.persistence.web.model.SessionData;
import vtb.app.domain.JwtToken;
import vtb.app.domain.UserData;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserDataWebRepositoryTest {
    private static final UserData userData = UserData.builder()
            .firstName("John")
            .lastName("Simpson")
            .patronymic("Silver")
            .login("GY56Jdn")
            .bkoId("1984130510")
            .client(List.of(UserData.Client.builder().bkoId("2924130510").inn("12342342342341").build(),
                    UserData.Client.builder().bkoId("23224130510").inn("123442342342341").build()))
            .build();
    public static final String SESSION_DATA_JSON = "{\"user\":{\"ids\":{\"mdmOCH\":\"1984130510\",\"login\":\"GY56Jdn\"}," +
            "\"activeClient\":null,\"lastName\":\"Simpson\",\"firstName\":\"John\",\"middleName\":\"Silver\"," +
            "\"position\":null,\"phones\":null,\"emails\":null},\"userClients\":[]}";

    public static final String JWT = "Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0YW10YW1AdnRiLnJ1IiwiaXNzIjoiaHR0cHM6Ly9wY" +
            "XNzcG9ydC52dGIucnUvcGFzc3BvcnQiLCJleHAiOiIxNTk2NjQ3Njg5IiwiaWF0IjoiMTU5NjY0NzE5MCIsImp0aSI6IjVlMzRnaDU" +
            "2Nzg5MHR5NzhubWtsNyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiXSwidHJ1c3QiOiJmYWxzZSIsIm5vbmNlIjoiNTQ2NDY0Z" +
            "GZzNWFmNHM2ZjU0NjQ2NCIsInJlYWxtIjoiIiwiY2hhbm5lbCI6ImFwaWMiLCJjdHhpIjoiMTIzZTQ1NjctZTg5Yi0xMmQzLWE0NTY" +
            "tNDI2NjU1NDQwMDAwIiwibWV0aG9kIjoibG9naW4iLCJzZmFjdG9yIjpbIm90cCIsInNtcyIsImJpbyJdfQ.jm2xNlqyO3x_u3W3MO" +
            "v20BtVaNOYrAmf7h2rGS5F6oUDWGwAJcCz9x0_5yURYUYD3TN8MopnmOUsH3dewvnfHA";

    public static final JwtToken jwtToken = JwtToken.builder().token(JWT).build();
    public static final String VALID_SESSION_ID = "123e4567-e89b-12d3-a456-426655440000";

    @Mock
    private UserDataWebClient httpClient;
    @Mock
    private Mapper<UserData, SessionData> mapper;
    @Spy
    @InjectMocks
    UserDataWebRepository userDataWebRepository;


    @Test
    void findBySessionId() throws IOException {
        // Так как у SessionData есть дефолтный конструктор и нет сеттеров, используется ObjectMapper и строка Json
        ObjectMapper objectMapper = new ObjectMapper();
        SessionData sessionData = objectMapper.readValue(SESSION_DATA_JSON, SessionData.class);
        given(httpClient.getSessionData(VALID_SESSION_ID,jwtToken.getToken())).willReturn(sessionData);
        given(mapper.mapFrom(sessionData)).willReturn(userData);
        Optional<UserData> expectedUserDataOptional = userDataWebRepository.findBySessionId(VALID_SESSION_ID,jwtToken);
        Assertions.assertTrue(expectedUserDataOptional.isPresent());
        Assertions.assertEquals(sessionData.getUser().getFirstName(), userData.getFirstName());
        Assertions.assertEquals(sessionData.getUser().getLastName(), userData.getLastName());
        Assertions.assertEquals(sessionData.getUser().getMiddleName(), userData.getPatronymic());
        Assertions.assertEquals(sessionData.getUser().getIds().getMdmOCH(), userData.getBkoId());
        Assertions.assertEquals(sessionData.getUser().getIds().getLogin(), userData.getLogin());
    }

    @Test
    void findBySessionIdThrowsSessionDataNotFoundException(){
        given(httpClient.getSessionData(VALID_SESSION_ID,jwtToken.getToken())).willThrow(SessionDataNotFoundException.class);
        Optional<UserData> expectedUserDataOptional = userDataWebRepository.findBySessionId(VALID_SESSION_ID,jwtToken);
        Assertions.assertTrue(expectedUserDataOptional.isEmpty());
    }

    @Test
    void findBySessionIdThrowsSessionDataServerException(){
        given(httpClient.getSessionData(VALID_SESSION_ID,jwtToken.getToken())).willThrow(SessionDataServerException.class);
        Optional<UserData> expectedUserDataOptional = userDataWebRepository.findBySessionId(VALID_SESSION_ID,jwtToken);
        Assertions.assertTrue(expectedUserDataOptional.isEmpty());
    }
}