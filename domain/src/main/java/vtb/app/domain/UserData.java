package vtb.app.domain;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UserData {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String login;
    private final String bkoId;
    private final String token;
    private final List<Client> client;

    @Getter
    @ToString
    @EqualsAndHashCode
    @Builder
    public static class Client {
        private final String inn;
        private final String bkoId;
    }
}