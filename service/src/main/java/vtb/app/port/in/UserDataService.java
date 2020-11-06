package vtb.app.port.in;

import vtb.app.domain.Security;
import vtb.app.domain.UserData;

public interface UserDataService {
    void processToken(String token);

    UserData getUserData(String id);

    UserData getUserData(String id, Security security);

    void sendUserData(UserData userData);
}
