package vtb.app.adapter.persistence.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataWebRepository implements UserDataRepository {
//    private final UserDataFeignClient feignClient;
    @Override
    public Optional<UserData> findBySessionId(String sessionId) {
        return Optional.empty();//Optional.ofNullable(feignClient.getData(sessionId));
    }
}

