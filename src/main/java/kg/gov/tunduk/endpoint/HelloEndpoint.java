package kg.gov.tunduk.endpoint;

import kg.gov.tunduk.domain.HelloRequest;
import kg.gov.tunduk.domain.HelloResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloEndpoint {

    @ResponsePayload
    @PayloadRoot(localPart = "helloRequest", namespace = "http://tunduk.gov.kg")
    public HelloResponse greet(@RequestPayload HelloRequest request) {
        HelloResponse response = new HelloResponse();
        response.setResult("Hello," + request.getName() + "!");
        return response;
    }
}
