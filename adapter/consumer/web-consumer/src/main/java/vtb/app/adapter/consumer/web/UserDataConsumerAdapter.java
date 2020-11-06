package vtb.app.adapter.consumer.web;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
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

    static final String EMPTY_OPERATION = "\"\"";

    private boolean isEmptyOperationName( String opName ) {
        return StringUtils.isEmpty( opName ) || EMPTY_OPERATION.equals( opName );
    }
    @Override
    public void sendUserData(UserData userData) {
        createRequestFromUserData( userData );
        TransferUserContexRequest transReq = createRequestFromUserData( userData );
        webServiceTemplate.marshalSendAndReceive( transReq, message -> {
            if( isEmptyOperationName( ((SoapMessage) message).getSoapAction() ) )
                ((SoapMessage) message).setSoapAction( "transferUserContex");
        } );

    }

    private TransferUserContexRequest createRequestFromUserData(UserData userData) {
        ObjectFactory objectFactory = new ObjectFactory();
        TransferUserContexRequest req = objectFactory.createTransferUserContexRequest();
        TransferUserContexRequest.UserName userName = objectFactory.createTransferUserContexRequestUserName();
        userName.setFirstName( userData.getFirstName());
        userName.setLastName( userData.getLastName());
        userName.setPatronymic( userData.getPatronymic() );
        req.setUserName( userName );
        TransferUserContexRequest.UserContext userContext = objectFactory.createTransferUserContexRequestUserContext();
        // fill userContext data
        userContext.setVOToken( userData.getToken() );
        userContext.setBKOId( userData.getBkoId() );
        userContext.setVtb24BOLogin( userData.getLogin() );
        req.setUserContext( userContext  );
        return req;
    }
}
