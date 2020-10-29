package vtb.app.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vtb.app.service.TokenService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TokenController {
    public static final String CONTEXT_USER_UUID_JSON_FIELD = "ctxi";
    private final TokenService tokenService;
    private final ObjectMapper objectMapper;

    @PostMapping(value ="/api/user/sendchattoken")
    public ResponseEntity<?> sendToken(@Parameter(hidden = true) @RequestHeader(name="Authorization") String jwt,
                                       @RequestParam String token ){
        String sessionId = getSessionId(jwt);
        return ResponseEntity.ok(sessionId);
    }

    private String getSessionId(String jwt) {
        String sessionId;
        String payload = jwt.substring(jwt.indexOf(".")+1, jwt.lastIndexOf("."));
        String decodedPayload = new String(new Base64().decode(payload.getBytes()));
        try {
            ObjectNode json = objectMapper.readValue(decodedPayload, ObjectNode.class);
            sessionId = json.get(CONTEXT_USER_UUID_JSON_FIELD).toString();
        } catch (NullPointerException | IOException exception){
            throw new IllegalArgumentException("Invalid jwt");
        }
        return sessionId;
    }
}
