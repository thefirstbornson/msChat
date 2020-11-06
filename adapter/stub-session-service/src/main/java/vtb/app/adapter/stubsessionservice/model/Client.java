package vtb.app.adapter.stubsessionservice.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Client {
    private String mdmOCH;
    private boolean isolated;
    private UserAuthority userAuthority;
    private OrgInfo orgInfo;
    private ServicePermissions servicePermissions;
    private List<Phone> phones;
    private List<Email> emails;
    private List<Address> addresses;
    private List<CrossReference> crossReferences;

    public String getOrgInn(){
        return orgInfo.getInn();
    }

    @Data
    private static class UserAuthority {
        private boolean eio;
        private int signaturePos;
        private String signMethod;
        private LocalDateTime signatureExpired;
        private boolean allowRead;
        private boolean allowCreate;
        private boolean allowEdit;
        private boolean allowDelete;
        private boolean allowSubmit;
        private boolean allowRevoke;
    }

    @Data
    private static class OrgInfo {
        private String kpp;
        private String kio;
        private String inn;
        private String ogrn;
        private String okpo;
        private String bik;
        private String swift;
        private String officialName;
        private String orgType;
        private String okopf;
        private String taxSystem;
    }

    @Data
    private static class ServicePermissions {
        private boolean ibSalaryCards;
        private boolean ibSalaryRegister;
    }

    @Data
    private static class Address {
        private String addressType;
        private String address;
    }

    @Data
    private static class CrossReference {
        private String system;
        private String externalId;
        private String expiredAt;
    }
}
