package vtb.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import vtb.app.domain.JwtToken;
import vtb.app.domain.UserData;
import vtb.app.service.exception.UserDataNotFoundException;
import vtb.app.port.out.UserDataConsumer;
import vtb.app.port.out.UserDataRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserDataServiceImplTest {
    private static final UserData userData = UserData.builder()
            .firstName("John")
            .lastName("Simpson")
            .patronymic("Silver")
            .login("GY56Jdn")
            .bkoId("1984130510")
            .client(List.of(UserData.Client.builder().bkoId("2924130510").inn("12342342342341").build(),
                    UserData.Client.builder().bkoId("23224130510").inn("123442342342341").build()))
            .build();
    public static final String JWT = "Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0YW10YW1AdnRiLnJ1IiwiaXNzIjoiaHR0cHM6Ly9wY" +
            "XNzcG9ydC52dGIucnUvcGFzc3BvcnQiLCJleHAiOiIxNTk2NjQ3Njg5IiwiaWF0IjoiMTU5NjY0NzE5MCIsImp0aSI6IjVlMzRnaDU" +
            "2Nzg5MHR5NzhubWtsNyIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiXSwidHJ1c3QiOiJmYWxzZSIsIm5vbmNlIjoiNTQ2NDY0Z" +
            "GZzNWFmNHM2ZjU0NjQ2NCIsInJlYWxtIjoiIiwiY2hhbm5lbCI6ImFwaWMiLCJjdHhpIjoiMTIzZTQ1NjctZTg5Yi0xMmQzLWE0NTY" +
            "tNDI2NjU1NDQwMDAwIiwibWV0aG9kIjoibG9naW4iLCJzZmFjdG9yIjpbIm90cCIsInNtcyIsImJpbyJdfQ.jm2xNlqyO3x_u3W3MO" +
            "v20BtVaNOYrAmf7h2rGS5F6oUDWGwAJcCz9x0_5yURYUYD3TN8MopnmOUsH3dewvnfHA";

    public static final JwtToken jwtToken = JwtToken.builder().token(JWT).build();
    public static final String VALID_SESSION_ID = "123e4567-e89b-12d3-a456-426655440000";

    @Mock
    UserDataConsumer userDataConsumer;
    @Mock
    UserDataRepository userDataRepository;
    @Spy
    @InjectMocks
    UserDataServiceImpl tokenService;

    @Test
    void getUserData() {
        given(userDataRepository.findById(VALID_SESSION_ID, jwtToken)).willReturn(Optional.ofNullable(userData));
        UserData expectedUserData = tokenService.getUserData(VALID_SESSION_ID, jwtToken);
        Assertions.assertEquals(userData, expectedUserData);
        verify(userDataRepository, times(1)).findById(VALID_SESSION_ID, jwtToken);
    }

    @Test
    void getUserDataFailed() {
        given(userDataRepository.findById(VALID_SESSION_ID, jwtToken)).willReturn(Optional.empty());
        Assertions.assertThrows(UserDataNotFoundException.class, () -> tokenService.getUserData(VALID_SESSION_ID, jwtToken));
    }
}