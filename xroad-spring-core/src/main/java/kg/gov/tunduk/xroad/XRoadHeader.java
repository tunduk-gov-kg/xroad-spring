package kg.gov.tunduk.xroad;

import kg.gov.tunduk.xroad.soap.ObjectType;
import kg.gov.tunduk.xroad.soap.XRoadClientId;
import kg.gov.tunduk.xroad.soap.XRoadServiceId;
import lombok.val;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

public class XRoadHeader implements WebServiceMessageCallback {

    private final static QName _UserId_QNAME = new QName("http://x-road.eu/xsd/xroad.xsd", "userId");
    private final static QName _Id_QNAME = new QName("http://x-road.eu/xsd/xroad.xsd", "id");
    private final static QName _ProtocolVersion_QNAME = new QName("http://x-road.eu/xsd/xroad.xsd", "protocolVersion");


    private final XRoadClientId consumer;
    private final XRoadServiceId producer;

    private final JAXBElement<String> protocolVersion;
    private final JAXBElement<String> messageId;
    private final JAXBElement<String> userId;

    public XRoadHeader(
            XRoadClientId consumer,
            XRoadServiceId producer,
            String messageId,
            String userId
    ) {
        this.consumer = consumer;
        this.producer = producer;

        this.protocolVersion = new JAXBElement<>(_ProtocolVersion_QNAME, String.class, null, "4.0");
        this.userId = new JAXBElement<>(_UserId_QNAME, String.class, null, userId);
        this.messageId = new JAXBElement<>(_Id_QNAME, String.class, null, messageId);
    }

    @Override
    public void doWithMessage(WebServiceMessage message) {
        SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();
        try {
            JAXBContext context = JAXBContext.newInstance(XRoadClientId.class, XRoadServiceId.class, ObjectType.class);
            Marshaller marshaller = context.createMarshaller();
            val soapHeaderResult = soapHeader.getResult();
            marshaller.marshal(this.protocolVersion, soapHeaderResult);
            marshaller.marshal(this.messageId, soapHeaderResult);
            marshaller.marshal(this.producer, soapHeaderResult);
            marshaller.marshal(this.consumer, soapHeaderResult);
            marshaller.marshal(this.userId, soapHeaderResult);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
