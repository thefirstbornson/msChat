package vtb.app.adapter.persistence.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import vtb.app.adapter.persistence.web.api.UserDataHttpClient;
import vtb.app.adapter.persistence.web.model.SessionData;

@Service
@RequiredArgsConstructor
public class UserDataWebClient implements UserDataHttpClient {
    private final WebClient webClient;

    @Override
    public SessionData getSessionData(String sessionId) {
        return webClient
                .get()
                .uri(String.join("", "/", sessionId))
                .retrieve()
                .bodyToMono(SessionData.class)
                .block();
    }
}
