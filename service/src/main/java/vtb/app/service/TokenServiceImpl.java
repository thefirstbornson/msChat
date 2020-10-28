package vtb.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vtb.app.domain.UserData;
import vtb.app.port.UserDataConsumer;


@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final UserDataConsumer userDataConsumer;

    @Override
    public void processToken(String token){
        UserData userData = getUserData(token);
    }

    @Override
    public UserData getUserData(String token){
        return null;
    }

    @Override
    public void sendUserData(UserData userData) {
        userDataConsumer.sendUserData(userData);
        System.out.println("Userdata send.");
    }


}
