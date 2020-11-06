package vtb.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vtb.app.domain.Security;
import vtb.app.service.exception.UserDataNotFoundException;
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
    public UserData getUserData(String id){
        throw  new UnsupportedOperationException();
    }

    @Override
    public UserData getUserData(String id, Security security) {
        return userDataRepository.findById(id, security)
                .orElseThrow(()->new UserDataNotFoundException(String.format("UserData not found by id: %s",id)));
    }

    @Override
    public void sendUserData(UserData userData) {
        userDataConsumer.sendUserData(userData);
        log.debug("Userdata was sent to external service successfully.");
    }


}
