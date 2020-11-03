package vtb.app.adapter.persistence.web;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import vtb.app.adapter.persistence.web.api.Mapper;
import vtb.app.adapter.persistence.web.model.Client;
import vtb.app.adapter.persistence.web.model.SessionData;
import vtb.app.domain.UserData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDataMapper implements Mapper<UserData, SessionData> {

    @Override
    public UserData mapFrom(SessionData sessionData) {
        List<UserData.Client> clients = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sessionData.getUserClients())){
            for (Client clientFromSessionData: sessionData.getUserClients()) {
                clients.add(UserData.Client.builder()
                        .inn(clientFromSessionData.getOrgInn())
                        .bkoId(clientFromSessionData.getMdmOCH())
                        .build()
                );
            }
        }

        return UserData.builder()
                .firstName(sessionData.getUser().getFirstName())
                .lastName(sessionData.getUser().getLastName())
                .patronymic(sessionData.getUser().getMiddleName())
                .bkoId(sessionData.getUser().getIds().getMdmOCH())
                .login(sessionData.getUser().getIds().getLogin())
                .client(Collections.unmodifiableList(clients))
                .build();
    }
}
