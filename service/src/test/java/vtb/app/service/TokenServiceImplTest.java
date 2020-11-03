package vtb.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataConsumer;
import vtb.app.port.out.UserDataRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {
    private static final UserData userData = UserData.builder()
            .firstName("John")
            .lastName("Simpson")
            .patronymic("Silver")
            .login("GY56Jdn")
            .bkoId("1984130510")
            .client(List.of(UserData.Client.builder().bkoId("2924130510").inn("12342342342341").build(),
                    UserData.Client.builder().bkoId("23224130510").inn("123442342342341").build()))
            .build();

    @Mock
    UserDataConsumer userDataConsumer;
    @Mock
    UserDataRepository userDataRepository;
    @Spy
    @InjectMocks
    TokenServiceImpl tokenService;

    @Test
    void getUserData() {
        given(userDataRepository.findBySessionId(anyString())).willReturn(Optional.ofNullable(userData));
        UserData expectedUserData = tokenService.getUserData(anyString());
        Assertions.assertEquals(userData, expectedUserData);
        verify(userDataRepository, times(1)).findBySessionId(anyString());
    }

    @Test
    void getUserDataFailed() {
        given(userDataRepository.findBySessionId(anyString())).willReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> tokenService.getUserData(anyString()));
    }
}