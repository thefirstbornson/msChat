package vtb.app.adapter.persistence.web.model;

import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private UserId ids;
    private ActiveClient activeClient;
    private String lastName;
    private String firstName;
    private String middleName;
    private String position;
    private List<Phone> phones;
    private List<Email> emails;

    @Getter
    @RequiredArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class UserId {
        private String mdmOCH;
        private String ACuniqueClientNumber;
        private String login;
    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class ActiveClient {
        private String mdmOCHClient;
    }
}
