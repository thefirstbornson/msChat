package vtb.app.port.out;

import vtb.app.domain.UserData;

public interface UserDataConsumer {
    void sendUserData(UserData userData);
}
