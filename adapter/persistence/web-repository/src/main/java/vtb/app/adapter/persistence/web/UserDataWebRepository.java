package vtb.app.adapter.persistence.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vtb.app.adapter.persistence.web.api.Mapper;
import vtb.app.adapter.persistence.web.api.UserDataHttpClient;
import vtb.app.adapter.persistence.web.model.SessionData;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataWebRepository implements UserDataRepository {
    private final UserDataHttpClient httpClient;
    private final Mapper<UserData, SessionData> mapper;

    @Override
    public Optional<UserData> findBySessionId(String sessionId) {
        SessionData sessionData = httpClient.getSessionData(sessionId);
        UserData userData = mapper.mapFrom(sessionData);
        return Optional.ofNullable(userData);
    }
}

