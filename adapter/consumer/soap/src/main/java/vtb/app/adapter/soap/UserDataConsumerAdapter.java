package vtb.app.adapter.soap;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import vtb.app.adapter.soap.wsdl.ObjectFactory;
import vtb.app.adapter.soap.wsdl.TransferUserContexRequest;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataConsumer;

@Service
public class UserDataConsumerAdapter implements UserDataConsumer {

    private WebServiceTemplate webServiceTemplate;

    public UserDataConsumerAdapter(WebServiceTemplate webServiceTemplate ) {
        this.webServiceTemplate = webServiceTemplate;
    }

    @Override
    public void sendUserData(UserData userData) {
        createRequestFromUserData( userData );
        TransferUserContexRequest transReq = createRequestFromUserData( userData );
        webServiceTemplate.marshalSendAndReceive( transReq );

    }

    private TransferUserContexRequest createRequestFromUserData(UserData userData) {
        ObjectFactory objectFactory = new ObjectFactory();
        TransferUserContexRequest req = objectFactory.createTransferUserContexRequest();
        TransferUserContexRequest.UserName un = objectFactory.createTransferUserContexRequestUserName();
        un.setFirstName( userData.getFirstName());
        un.setLastName( userData.getLastName());
        un.setPatronymic( userData.getPatronymic() );
        req.setUserName( un );
        TransferUserContexRequest.UserContext uc = objectFactory.createTransferUserContexRequestUserContext();
        // fill uc data
        uc.setVOToken( userData.getToken() );
        uc.setBKOId( userData.getBkoId() );
        uc.setVtb24BOLogin( userData.getLogin() );
        req.setUserContext( uc  );
        return req;
    }
}
