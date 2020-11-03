package vtb.app.adapter.persistence.web.model;

import lombok.Data;

import java.util.List;

@Data
public class SessionData {
    User user;
    List<Client> userClients;
}
