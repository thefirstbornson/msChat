package vtb.app.adapter.soap;

import org.springframework.stereotype.Service;
import vtb.app.adapter.soap.wsdl.ObjectFactory;
import vtb.app.adapter.soap.wsdl.TransferUserContexRequest;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataConsumer;

@Service
public class UserDataConsumerAdapter implements UserDataConsumer {

    @Override
    public void sendUserData(UserData userData) {
        ObjectFactory objectFactory = new ObjectFactory();
        TransferUserContexRequest transferUserContexRequest = objectFactory.createTransferUserContexRequest();
    }
}
