package vtb.app.adapter.persistence.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import vtb.app.adapter.persistence.web.api.UserDataHttpClient;
import vtb.app.adapter.persistence.web.exception.SessionDataNotFoundException;
import vtb.app.adapter.persistence.web.exception.SessionDataServerException;
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
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new SessionDataNotFoundException("SessionData not found")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new SessionDataServerException("SessionData server internal error")))
                .bodyToMono(SessionData.class)
                .block();
    }
}
