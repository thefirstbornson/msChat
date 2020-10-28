package vtb.app.adapter.soap;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vtb.app.adapter.soap.wsdl.ObjectFactory;
import vtb.app.adapter.soap.wsdl.TransferUserContexRequest;
import vtb.app.domain.UserData;
import vtb.app.port.UserDataConsumer;

@Component
public class UserDataConsumerImpl implements UserDataConsumer {

    @Override
    public void sendUserData(UserData userData) {
        ObjectFactory objectFactory = new ObjectFactory();
        TransferUserContexRequest transferUserContexRequest = objectFactory.createTransferUserContexRequest();
    }
}
