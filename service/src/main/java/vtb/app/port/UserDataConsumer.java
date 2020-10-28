package vtb.app.port;

import vtb.app.domain.UserData;

public interface UserDataConsumer {
    void sendUserData(UserData userData);
}
