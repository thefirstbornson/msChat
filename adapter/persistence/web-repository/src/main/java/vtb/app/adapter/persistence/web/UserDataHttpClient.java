package vtb.app.adapter.persistence.web;

import vtb.app.adapter.persistence.web.model.SessionData;

public interface UserDataHttpClient {
    SessionData getSessionData(String sessionId);
}
