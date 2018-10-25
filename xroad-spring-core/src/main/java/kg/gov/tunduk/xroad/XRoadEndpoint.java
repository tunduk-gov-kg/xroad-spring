package kg.gov.tunduk.xroad;

import kg.gov.tunduk.xroad.soap.XRoadObjectType;
import kg.gov.tunduk.xroad.soap.XRoadClientId;
import kg.gov.tunduk.xroad.soap.XRoadServiceId;
import lombok.Getter;
import org.springframework.util.Assert;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.util.Iterator;

@Getter
public class XRoadEndpoint {
    private String userId;
    private String messageId;
    private String protocolVersion;

    private XRoadClientId consumer;
    private XRoadServiceId producer;

    private Unmarshaller unmarshaller;

    public XRoadEndpoint() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XRoadClientId.class, XRoadServiceId.class, XRoadObjectType.class);
            this.unmarshaller = jaxbContext.createUnmarshaller();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    void initialize(SoapHeader soapHeader) throws JAXBException {
        Iterator<SoapHeaderElement> id = soapHeader.examineHeaderElements(
                new QName("http://x-road.eu/xsd/xroad.xsd", "id"));
        Assert.isTrue(id.hasNext(), "Expected message id");
        this.messageId = (id.next().getText());

        Iterator<SoapHeaderElement> protocolVersion = soapHeader.examineHeaderElements(
                new QName("http://x-road.eu/xsd/xroad.xsd", "protocolVersion"));
        Assert.isTrue(protocolVersion.hasNext(), "Expected protocol version");
        this.protocolVersion = (protocolVersion.next().getText());

        Iterator<SoapHeaderElement> userId = soapHeader.examineHeaderElements(new QName("http://x-road.eu/xsd/xroad.xsd", "userId"));
        if (userId.hasNext()) {
            this.userId = (userId.next().getText());
        }

        Iterator<SoapHeaderElement> client = soapHeader.examineHeaderElements(new QName("http://x-road.eu/xsd/xroad.xsd", "client"));
        Assert.isTrue(client.hasNext(), "Expected client identifier");
        this.consumer = ((XRoadClientId) unmarshaller.unmarshal(client.next().getSource()));

        Iterator<SoapHeaderElement> producer = soapHeader.examineHeaderElements(new QName("http://x-road.eu/xsd/xroad.xsd", "service"));
        Assert.isTrue(producer.hasNext(), "Expected service identifier");
        this.producer = ((XRoadServiceId) unmarshaller.unmarshal(producer.next().getSource()));
    }
}
