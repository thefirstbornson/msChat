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
import vtb.app.domain.UserData;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
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
        given(httpClient.getSessionData(anyString())).willReturn(sessionData);
        given(mapper.mapFrom(sessionData)).willReturn(userData);
        Optional<UserData> expectedUserDataOptional = userDataWebRepository.findBySessionId(anyString());
        Assertions.assertTrue(expectedUserDataOptional.isPresent());
        Assertions.assertEquals(sessionData.getUser().getFirstName(), userData.getFirstName());
        Assertions.assertEquals(sessionData.getUser().getLastName(), userData.getLastName());
        Assertions.assertEquals(sessionData.getUser().getMiddleName(), userData.getPatronymic());
        Assertions.assertEquals(sessionData.getUser().getIds().getMdmOCH(), userData.getBkoId());
        Assertions.assertEquals(sessionData.getUser().getIds().getLogin(), userData.getLogin());
    }

    @Test
    void findBySessionIdThrowsSessionDataNotFoundException(){
        given(httpClient.getSessionData(anyString())).willThrow(SessionDataNotFoundException.class);
        Optional<UserData> expectedUserDataOptional = userDataWebRepository.findBySessionId(anyString());
        Assertions.assertTrue(expectedUserDataOptional.isEmpty());
    }

    @Test
    void findBySessionIdThrowsSessionDataServerException(){
        given(httpClient.getSessionData(anyString())).willThrow(SessionDataServerException.class);
        Optional<UserData> expectedUserDataOptional = userDataWebRepository.findBySessionId(anyString());
        Assertions.assertTrue(expectedUserDataOptional.isEmpty());
    }
}