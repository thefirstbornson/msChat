package vtb.app.adapter.persistence.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vtb.app.domain.UserData;

//@FeignClient(name = "simple-client", url = "http://localhost:8080")
public interface UserDataFeignClient {

    @GetMapping("/session/{sessionId}")
    UserData getData(@PathVariable String sessionId);
}
