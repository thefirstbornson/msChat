package vtb.app.port.out;

import vtb.app.domain.Security;
import vtb.app.domain.UserData;

import java.util.Optional;

public interface UserDataRepository {
    Optional<UserData> findById(String sessionId);
    Optional<UserData> findById(String sessionId, Security security);
}
