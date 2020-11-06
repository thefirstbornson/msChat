package vtb.app.port.in;

import vtb.app.domain.Security;
import vtb.app.domain.UserData;

public interface UserDataService {

    UserData getUserData(String id);

    UserData getUserData(String id, Security security);

    void sendUserData(UserData userData);
}
