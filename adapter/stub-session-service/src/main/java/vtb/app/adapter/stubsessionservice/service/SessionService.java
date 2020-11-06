package vtb.app.adapter.stubsessionservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vtb.app.adapter.stubsessionservice.model.SessionData;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class SessionService {

    private static Logger log = LoggerFactory.getLogger(SessionService.class);

    @Autowired
    @Qualifier("sessionTable")
    private Map<String, SessionData> table;

    @Autowired
    @Qualifier("mapper")
    private ObjectMapper mapper;

    public CompletableFuture<SessionData> getBySession(String session){
        SessionData result = null;
        log.info(String.format("%s %s", "Trying to find any user with id: ", session));
        if(table.containsKey(session)) {
            result = table.get(session);
        }
        return CompletableFuture.completedFuture(result);
    }

}
