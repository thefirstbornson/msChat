package vtb.app.adapter.stubsessionservice.model;

import lombok.*;

import java.util.List;

@Data
@Builder
public class SessionData {
    private User user;
    private List<Client> userClients;
}
