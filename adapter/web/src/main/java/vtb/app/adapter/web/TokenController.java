package vtb.app.adapter.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtb.app.adapter.web.exception.InvalidJWTException;
import vtb.app.domain.JwtToken;
import vtb.app.domain.Security;
import vtb.app.domain.UserData;
import vtb.app.port.in.UserDataService;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
public class TokenController {
    public static final String CONTEXT_USER_UUID_JSON_FIELD = "ctxi";
    private final UserDataService tokenService;
    private final ObjectMapper objectMapper;

    @PostMapping(value ="/api/user/sendchattoken")
    public ResponseEntity<?> sendToken(@Parameter(hidden = true) @RequestHeader(name="Authorization") String jwt,
                                       @RequestParam String token ){
        String sessionId = getSessionId(jwt);
        Security security = JwtToken.builder().token(jwt).build();
        UserData userData = tokenService.getUserData(sessionId, security);
        userData = userData.toBuilder().token(token).build();
        tokenService.sendUserData(userData);
        return ResponseEntity.ok(userData);
    }

    private String getSessionId(String jwt) {
        String sessionId;
        String payload = jwt.substring(jwt.indexOf(".")+1, jwt.lastIndexOf("."));
        String decodedPayload = new String(Base64.getDecoder().decode(payload.getBytes()));
        try {
            ObjectNode json = objectMapper.readValue(decodedPayload, ObjectNode.class);
            sessionId = json.get(CONTEXT_USER_UUID_JSON_FIELD).asText();
        } catch (NullPointerException | IOException exception){
            throw new InvalidJWTException("Invalid jwt");
        }
        return sessionId;
    }
}
