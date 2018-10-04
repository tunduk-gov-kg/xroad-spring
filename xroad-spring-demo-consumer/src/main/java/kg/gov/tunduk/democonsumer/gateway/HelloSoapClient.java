package kg.gov.tunduk.democonsumer.gateway;

import kg.gov.tunduk.democonsumer.domain.soap.HelloRequest;
import kg.gov.tunduk.democonsumer.domain.soap.HelloResponse;
import kg.gov.tunduk.xroad.XRoadHeader;
import kg.gov.tunduk.xroad.soap.XRoadServiceId;
import kg.gov.tunduk.xroad.soap.XRoadSubSystemId;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.UUID;

public class HelloSoapClient extends WebServiceGatewaySupport {

    private final XRoadSubSystemId consumerId;
    private final XRoadServiceId producerId;

    public HelloSoapClient(
            XRoadSubSystemId consumerId,
            XRoadServiceId producerId
    ) {
        this.consumerId = consumerId;
        this.producerId = producerId;
    }

    public String process(String name) {
        HelloRequest request = new HelloRequest();
        request.setName(name);

        HelloResponse response = (HelloResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request, new XRoadHeader(consumerId, producerId, generateId(), "CONSUMER_ID")
                );

        return response.getGreeting();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}
