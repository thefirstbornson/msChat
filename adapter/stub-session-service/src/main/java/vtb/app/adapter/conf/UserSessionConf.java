package vtb.app.adapter.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vtb.app.adapter.model.SessionData;
import vtb.app.adapter.model.User;

import java.util.*;

@Configuration
public class UserSessionConf {

    private static final SessionData SESSION_DATA = SessionData.builder()
                                        .user(User.builder()
                                                .ids(User.UserId.builder().login("GY56Jdn").mdmOCH("1984130510").build())
                                                .firstName("John")
                                                .lastName("Simpson")
                                                .middleName("Silver")
                                                .build())
                                        .userClients(Collections.EMPTY_LIST)
                                        .build();

    @Bean("sessionTable")
    public Map<String, SessionData> sessionTable(){
        return new HashMap<>(){{
            put("123e4567-e89b-12d3-a456-426655440000", SESSION_DATA);
            put("123e4567-e89b-12d3-a456-426655440001", SESSION_DATA);
            put("123e4567-e89b-12d3-a456-426655440002", SESSION_DATA);
            put("123e4567-e89b-12d3-a456-426655440003", SESSION_DATA);
            put("123e4567-e89b-12d3-a456-426655440004", SESSION_DATA);
        }};
    }

    @Bean("mapper")
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }

}
