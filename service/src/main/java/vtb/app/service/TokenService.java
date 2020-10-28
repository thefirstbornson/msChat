package vtb.app.service;

import vtb.app.domain.UserData;

public interface TokenService {
    void processToken(String token);

    UserData getUserData(String token);

    void sendUserData(UserData userData);
}
