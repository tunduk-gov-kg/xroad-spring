package kg.gov.tunduk.xroad;

import kg.gov.tunduk.xroad.soap.XRoadServiceId;
import kg.gov.tunduk.xroad.soap.XRoadSubSystemId;
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


    private final XRoadSubSystemId consumer;
    private final XRoadServiceId producer;

    private final JAXBElement<String> protocolVersion;
    private final JAXBElement<String> messageId;
    private final JAXBElement<String> userId;

    public XRoadHeader(
            XRoadSubSystemId consumer,
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
            JAXBContext context = JAXBContext.newInstance(XRoadSubSystemId.class, XRoadServiceId.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(this.protocolVersion, soapHeader.getResult());
            marshaller.marshal(this.messageId, soapHeader.getResult());
            marshaller.marshal(this.producer, soapHeader.getResult());
            marshaller.marshal(this.consumer, soapHeader.getResult());
            marshaller.marshal(this.userId, soapHeader.getResult());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
