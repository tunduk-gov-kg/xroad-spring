package kg.gov.tunduk.democonsumer.config;

import kg.gov.tunduk.democonsumer.domain.soap.HelloRequest;
import kg.gov.tunduk.democonsumer.domain.soap.HelloResponse;
import kg.gov.tunduk.democonsumer.gateway.HelloSoapClient;
import kg.gov.tunduk.xroad.XRoadConfiguration;
import kg.gov.tunduk.xroad.soap.XRoadServiceId;
import kg.gov.tunduk.xroad.soap.XRoadSubSystemId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class ConsumerConfiguration extends XRoadConfiguration {


    @Bean
    public HelloSoapClient helloSoapClient() {
        HelloSoapClient client = new HelloSoapClient(
                consumerId(), producerId()
        );

        client.setMarshaller(marshaller());
        client.setUnmarshaller(marshaller());
        client.setDefaultUri("http://localhost:8080/ws/hello");
        return client;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(
                HelloRequest.class,
                HelloResponse.class
        );
        return marshaller;
    }

    @Bean
    public XRoadSubSystemId consumerId() {
        XRoadSubSystemId consumerId = new XRoadSubSystemId();
        consumerId.setInstance("KG");
        consumerId.setMemberClass("GOV");
        consumerId.setMemberCode("CONSUMER");
        consumerId.setSubSystemCode("CONSUMER_SUBSYSTEM");
        return consumerId;
    }

    @Bean
    public XRoadServiceId producerId() {
        XRoadServiceId producerId = new XRoadServiceId();
        producerId.setInstance("KG");
        producerId.setMemberClass("GOV");
        producerId.setMemberCode("PRODUCER");
        producerId.setSubSystemCode("PRODUCER_SUBSYSTEM");
        producerId.setServiceCode("SOME_SERVICE");
        return producerId;
    }
}
