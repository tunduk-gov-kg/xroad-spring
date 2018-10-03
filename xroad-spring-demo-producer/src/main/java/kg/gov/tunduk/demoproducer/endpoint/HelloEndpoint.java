package kg.gov.tunduk.demoproducer.endpoint;

import kg.gov.tunduk.demoproducer.domain.soap.HelloRequest;
import kg.gov.tunduk.demoproducer.domain.soap.HelloResponse;
import kg.gov.tunduk.xroad.XRoadEndpoint;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloEndpoint extends XRoadEndpoint {

    @ResponsePayload
    @PayloadRoot(localPart = "helloRequest", namespace = "http://tunduk.gov.kg")
    public HelloResponse greet(@RequestPayload HelloRequest request) {
        HelloResponse response = new HelloResponse();
        response.setGreeting(String.format("Hello, %s!", request.getName()));
        return response;
    }
}
