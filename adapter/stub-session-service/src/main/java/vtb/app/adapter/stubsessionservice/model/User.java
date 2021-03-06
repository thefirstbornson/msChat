package vtb.app.adapter.stubsessionservice.model;

import lombok.*;

import java.util.List;

@Data
@Builder
public class User {
    private UserId ids;
    private ActiveClient activeClient;
    private String lastName;
    private String firstName;
    private String middleName;
    private String position;
    private List<Phone> phones;
    private List<Email> emails;

    @Data
    @Builder
    public static class UserId {
        private String mdmOCH;
        private String ACuniqueClientNumber;
        private String login;
    }

    @Data
    public static class ActiveClient {
        private String mdmOCHClient;
    }
}
