package vtb.app.adapter.persistence.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vtb.app.adapter.persistence.web.model.SessionData;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataWebRepository implements UserDataRepository {
    private final UserDataHttpClient httpClient;
    @Override
    public Optional<UserData> findBySessionId(String sessionId) {
        SessionData sessionData = httpClient.getSessionData(sessionId);
        UserData userData = UserData.builder()
                .firstName(sessionData.getUser().getFirstName())
                .lastName(sessionData.getUser().getLastName())
                .patronymic(sessionData.getUser().getMiddleName())
                .bkoId(sessionData.getUser().getIds().getMdmOCH())
                .login(sessionData.getUser().getIds().getLogin())
                .build();
        return Optional.ofNullable(userData);
    }
}

