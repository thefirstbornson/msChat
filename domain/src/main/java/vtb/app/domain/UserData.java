package vtb.app.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserData {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String bkoId;
    private String token;
    private List<Client> client;

    @Data
    public static class Client {
        private String inn;
        private String bkoId;
    }

}
