package vtb.app.adapter.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Client {
    String mdmOCH;
    boolean isolated;
    UserAuthority userAuthority;
    OrgInfo orgInfo;
    ServicePermissions servicePermissions;
    List<Phone> phones;
    List<Email> emails;
    List<Address> addresses;
    List<CrossReference> crossReferences;

    @Data
    private static class UserAuthority {
        boolean eio;
        int signaturePos;
        String signMethod;
        LocalDateTime signatureExpired;
        boolean allowRead;
        boolean allowCreate;
        boolean allowEdit;
        boolean allowDelete;
        boolean allowSubmit;
        boolean allowRevoke;
    }

    @Data
    private static class OrgInfo {
        String kpp;
        String kio;
        String inn;
        String ogrn;
        String okpo;
        String bik;
        String swift;
        String officialName;
        String orgType;
        String okopf;
        String taxSystem;
    }

    @Data
    private static class ServicePermissions {
        boolean ibSalaryCards;
        boolean ibSalaryRegister;
    }

    @Data
    private static class Address {
        String addressType;
        String address;
    }

    @Data
    private static class CrossReference {
        String system;
        String externalId;
        String expiredAt;
    }
}
