package vtb.app.adapter.soapstub.conf;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
@Slf4j
public class SoapStubServiceConfig {

    @Bean
    public WireMockServer stubServer(@Value( "${application.virtdep.ws.stub.port:4545}") int port){
        WireMockServer server = new WireMockServer(port);
        createStubs().forEach(server::addStubMapping  );
        server.start();
        log.debug("wiremock server started on port {}", port );
        return server;
    }

    private List<StubMapping> createStubs( ){
        List<StubMapping> stubs = new ArrayList<>();
        stubs.add( createMappingForContextRequest());
        stubs.add( createMappingForDumpData());
        return stubs;
    }

    @SneakyThrows
    private String loadResponseData(String fileName ){
        return IOUtils.toString( getClass().getResourceAsStream( "/__files/" + fileName ), StandardCharsets.UTF_8);
    }

    private  StubMapping createMappingForContextRequest(){
        return WireMock
                .post( "/")
                .withHeader( "SOAPAction", containing( "transferUserContex") )
                .withRequestBody( matching( ".*") )
                .willReturn( aResponse()
                        .withStatus(200)
                        .withBody( loadResponseData( "emptyResponse.xml") )
                        .withHeader( "Content-Type", "text/xml; charset=utf-8")
                ).build();
    }
    private  StubMapping createMappingForDumpData(){
        return WireMock
                .post( "/")
                .withHeader( "SOAPAction", containing( "dumpData") )
                .withRequestBody( matching( ".*") )
                .willReturn( aResponse()
                        .withStatus(200)
                        .withBody( loadResponseData( "emptyResponse.xml") )
                        .withHeader( "Content-Type", "text/xml; charset=utf-8")
                ).build();
    }

}
