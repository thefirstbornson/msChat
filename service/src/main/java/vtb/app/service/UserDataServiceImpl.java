package vtb.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vtb.app.domain.Security;
import vtb.app.exception.UserDataNotFoundException;
import vtb.app.port.in.UserDataService;
import vtb.app.port.out.UserDataConsumer;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserDataServiceImpl implements UserDataService {
    private final UserDataConsumer userDataConsumer;
    private final UserDataRepository userDataRepository;

    @Override
    public void processToken(String token){
        UserData userData = getUserData(token);
        userData.toBuilder().token(token);

    }

    @Override
    public UserData getUserData(String sessionId){
        throw  new UnsupportedOperationException();
    }

    @Override
    public UserData getUserData(String sessionId, Security security) {
        return userDataRepository.findBySessionId(sessionId, security)
                .orElseThrow(()->new UserDataNotFoundException(String.format("UserData not found by sessionId: %s",sessionId)));
    }

    @Override
    public void sendUserData(UserData userData) {
        userDataConsumer.sendUserData(userData);
        log.debug("Userdata was sent to soap service successfully.");
    }


}
