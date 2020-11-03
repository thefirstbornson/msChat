package vtb.app.adapter.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vtb.app.adapter.model.SessionData;
import vtb.app.adapter.model.User;

import java.util.*;
import java.util.function.Supplier;

@Configuration
public class UserSessionConf {

    @Bean("sessionTable")
    public Map<String, SessionData> sessionTable(){
        return new HashMap<>(){{
//            put("123e4567-e89b-12d3-a456-426655440000", SessionUser.get());
            put("123e4567-e89b-12d3-a456-426655440000", SessionData.builder()
                    .user(User.builder()
                            .ids(User.UserId.builder().login("GY56Jdn").mdmOCH("1984130510").build())
                            .firstName("John")
                            .lastName("Simpson")
                            .middleName("Silver")
                            .build())
                    .userClients(Collections.EMPTY_LIST)
                    .build());
//            put("123e4567-e89b-12d3-a456-426655440001", SessionUser.get());
//            put("123e4567-e89b-12d3-a456-426655440002", SessionUser.get());
//            put("123e4567-e89b-12d3-a456-426655440003", SessionUser.get());
//            put("123e4567-e89b-12d3-a456-426655440004", SessionUser.get());
        }};
    }

    @Bean("mapper")
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }

//    @AllArgsConstructor
//    @Getter
//    public static class SessionUser {
//        private final String fName;
//        private final String lName;
//        private final String mName;
//        private final String login;
//        private final String userID;
//        private final String inn;
//        private final String clientID;
//
//        public static SessionUser get() {
//            return new SessionUser(UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString());
//        }
//    }

}
