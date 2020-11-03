package vtb.app.adapter.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SessionData {
    User user;
    List<Client> userClients;
}
