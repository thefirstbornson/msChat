package vtb.app.adapter.persistence.web.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    UserId ids;
    ActiveClient activeClient;
    String lastName;
    String firstName;
    String middleName;
    String position;
    List<Phone> phones;
    List<Email> emails;

    @Data
    public static class UserId {
        private String mdmOCH;
        private String ACuniqueClientNumber;
        private String login;
    }

    @Data
    public static class ActiveClient {
        String mdmOCHClient;
    }
}
