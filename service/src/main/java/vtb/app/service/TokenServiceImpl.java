package vtb.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vtb.app.exception.UserDataNotFoundException;
import vtb.app.port.in.TokenService;
import vtb.app.port.out.UserDataConsumer;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataRepository;


@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final UserDataConsumer userDataConsumer;
    private final UserDataRepository userDataRepository;

    @Override
    public void processToken(String token){
        UserData userData = getUserData(token);

    }

    @Override
    public UserData getUserData(String sessionId){
        return userDataRepository.findBySessionId(sessionId)
                .orElseThrow(()->new UserDataNotFoundException(String.format("UserData not found by sessionId: %s",sessionId)));
    }

    @Override
    public void sendUserData(UserData userData) {
        userDataConsumer.sendUserData(userData);
        System.out.println("Userdata send.");
    }


}
