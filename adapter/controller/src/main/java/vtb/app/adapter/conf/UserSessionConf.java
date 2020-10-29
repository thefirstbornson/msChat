package vtb.app.adapter.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

@Configuration
public class UserSessionConf {

    @Bean("sessionTable")
    public Map<String, SessionUser> sessionTable(){
        Map<String, SessionUser> map = new HashMap<>(){{
            put("123e4567-e89b-12d3-a456-426655440000", SessionUser.get());
            put("123e4567-e89b-12d3-a456-426655440001", SessionUser.get());
            put("123e4567-e89b-12d3-a456-426655440002", SessionUser.get());
            put("123e4567-e89b-12d3-a456-426655440003", SessionUser.get());
            put("123e4567-e89b-12d3-a456-426655440004", SessionUser.get());
        }};
        return map;
    }

    @Bean("mapper")
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }

    @AllArgsConstructor
    @Getter
    public static class SessionUser {
        private final String fName;
        private final String lName;
        private final String mName;
        private final String login;
        private final String userID;
        private final String inn;
        private final String clientID;

        public static SessionUser get() {
            return new SessionUser(UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }

}
