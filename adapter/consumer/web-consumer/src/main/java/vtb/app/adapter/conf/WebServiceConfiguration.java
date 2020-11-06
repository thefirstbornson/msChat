package vtb.app.adapter.conf;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class WebServiceConfiguration {

    @Bean(name="webServiceTemplate")
    public WebServiceTemplate webServiceTemplate(@Value( "${application.vo.ws.uri:http://localhost:8080/ws}") String wsUri ) {
        return createWebServiceTemplate( wsUri );
    }

    @SneakyThrows
    public WebServiceTemplate createWebServiceTemplate(String uri ){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan( "vtb.app.adapter.soap.wsdl");
        WebServiceTemplate wst = new WebServiceTemplate();
        wst.setDefaultUri( uri );
        wst.setMarshaller( marshaller );
        wst.setUnmarshaller( marshaller );
        return wst;
    }
}
