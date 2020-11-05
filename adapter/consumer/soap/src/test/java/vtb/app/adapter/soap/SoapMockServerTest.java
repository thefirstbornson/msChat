package vtb.app.adapter.soap;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lanwen.wiremock.ext.WiremockResolver;
import ru.lanwen.wiremock.ext.WiremockUriResolver;
import vtb.app.TestApplication;
import vtb.app.adapter.conf.WebServiceConfiguration;
import vtb.app.domain.UserData;
import vtb.app.port.out.UserDataConsumer;
import vtb.app.port.out.UserDataRepository;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;


@ExtendWith({
        SpringExtension.class,
        WiremockResolver.class,
        WiremockUriResolver.class
} )
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestApplication.class})
public class SoapMockServerTest {


    @Autowired
    WebServiceConfiguration config;

    UserDataConsumer dataConsumer;

    @MockBean
    UserDataRepository userDataRepositroy;

    WireMockServer wireMockServer;
    String mockUri;

    private static final UserData userData = UserData.builder()
            .firstName("John")
            .lastName("Simpson")
            .patronymic("Silver")
            .login("GY56Jdn")
            .bkoId("1984130510")
            .client(List.of(UserData.Client.builder().bkoId("2924130510").inn("12342342342341").build(),
                    UserData.Client.builder().bkoId("23224130510").inn("123442342342341").build()))
            .build();

    @BeforeEach
    void setup( @WiremockResolver.Wiremock WireMockServer server,
                @WiremockUriResolver.WiremockUri String uri ) {
        System.out.println("using uri: " + uri);
        mockUri = uri;
        wireMockServer = server;
        setupStub( "emptyResponse.xml" );
        wireMockServer.start();
        setupMocks();
    }

    private void setupMocks(){
        dataConsumer = new UserDataConsumerAdapter( config.createWebServiceTemplate( mockUri ));
    }

    @SneakyThrows
    void setupStub( String stubFile ){
        String responseData  = "";
        if( stubFile != null )
            responseData = IOUtils.toString( getClass().getResourceAsStream( "/__files/" + stubFile ), StandardCharsets.UTF_8);
        wireMockServer.stubFor(WireMock
                .post( "/")
                .withRequestBody( matching( ".*") )
                .willReturn( aResponse()
                        .withStatus(200)
                        .withBody( responseData )
                        .withHeader( "Content-Type", "text/xml; charset=utf-8")
                )
        );
    }

    @Test
    public void testFirst( ){
        dataConsumer.sendUserData(userData);
    }
}
