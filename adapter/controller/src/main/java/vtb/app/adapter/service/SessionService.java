package vtb.app.adapter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vtb.app.adapter.conf.UserSessionConf;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class SessionService {

    private static Logger log = LoggerFactory.getLogger(SessionService.class);

    @Autowired
    @Qualifier("sessionTable")
    private Map<String, UserSessionConf.SessionUser> table;

    @Autowired
    @Qualifier("mapper")
    private ObjectMapper mapper;

    public CompletableFuture<String> getBySession(String session){
        String result = null;
        log.info(String.format("%s %s", "Trying to find any user with id: ", session));
        try{
            if(table.containsKey(session)) {
                result = mapper.writeValueAsString(table.get(session));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(result);
    }

}
