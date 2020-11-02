package vtb.app.adapter.persistence.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserDataWebClient implements UserDataHttpClient {
    private final WebClient webClient;

    @Override
    public Object getSessionData(String sessionId) {
        return webClient
                .get()
                .uri(String.join("", "/session/", sessionId))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
