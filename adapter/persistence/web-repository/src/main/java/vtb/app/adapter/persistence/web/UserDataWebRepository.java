package vtb.app.adapter.persistence.web;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vtb.app.adapter.persistence.web.api.Mapper;
import vtb.app.adapter.persistence.web.api.UserDataHttpClient;
import vtb.app.adapter.persistence.web.exception.SessionDataNotFoundException;
import vtb.app.adapter.persistence.web.exception.SessionDataServerException;
import vtb.app.adapter.persistence.web.model.SessionData;
import vtb.app.domain.JwtToken;
import vtb.app.domain.Security;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataWebRepository implements UserDataRepository {
    private static Logger log = LoggerFactory.getLogger(UserDataWebRepository.class);
    private final UserDataHttpClient httpClient;
    private final Mapper<UserData, SessionData> mapper;

    @Override
    public Optional<UserData> findBySessionId(String sessionId) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public Optional<UserData> findBySessionId(String sessionId, Security security) {
        UserData userData = null;
        try {
            JwtToken jwtToken = (JwtToken) security;
            SessionData sessionData = httpClient.getSessionData(sessionId, jwtToken.getToken());
            userData = mapper.mapFrom(sessionData);
        } catch (SessionDataNotFoundException | SessionDataServerException e){
            log.error(e.getMessage());
        }
        return Optional.ofNullable(userData);
    }
}

