package vtb.app.port.out;

import vtb.app.domain.UserData;

import java.util.Optional;

public interface UserDataRepository {
    Optional<UserData> findBySessionId(String sessionId);
}
