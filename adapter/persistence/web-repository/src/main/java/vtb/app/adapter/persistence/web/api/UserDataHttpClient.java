package vtb.app.adapter.persistence.web.api;

import vtb.app.adapter.persistence.web.model.SessionData;

public interface UserDataHttpClient {
    SessionData getSessionData(String sessionId);
    SessionData getSessionData(String sessionId, String jwt);
}
