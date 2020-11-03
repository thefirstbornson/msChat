package vtb.app.adapter.persistence.web.model;

import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class SessionData {
    private User user;
    private List<Client> userClients;
}
