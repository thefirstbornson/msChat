package vtb.app.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vtb.app.adapter.service.SessionService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api/session")
public class SessionDataController {

    private static final Logger log = LoggerFactory.getLogger(SessionDataController.class);

    @Autowired
    private SessionService sessionService;

    @PostMapping("/sessionId")
    public ResponseEntity<?> getContext(@RequestParam("sessionId") String sessionId){
        log.info(String.format("%s %s", "GOT sessionID: ", sessionId));
        CompletableFuture<String> userFromStorage = sessionService.getBySession(sessionId);
        if(userFromStorage.isCancelled() || userFromStorage.isCompletedExceptionally()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }else{
            try {
                String user = userFromStorage.get();
                log.info(String.format("%s %s", "GOT user: ", user));
                if(user != null){
                    return ResponseEntity.ok(user);
                }else {
                    log.info(String.format("%s %s", "No user found with id: ", sessionId));
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }
    }

}
