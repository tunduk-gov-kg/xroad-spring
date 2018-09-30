package kg.gov.tunduk.configuration;

import org.springframework.ws.wsdl.wsdl11.provider.SuffixBasedMessagesProvider;

import javax.wsdl.Definition;
import javax.wsdl.Message;
import javax.wsdl.Part;
import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;

public class XRoadMessagesProvider extends SuffixBasedMessagesProvider {

    @Override
    public void addMessages(Definition definition) throws WSDLException {
        definition.addNamespace("xrd", "http://x-road.eu/xsd/xroad.xsd");
        definition.addMessage(createXRoadHeaderMessage(definition));
        super.addMessages(definition);
    }

    private Message createXRoadHeaderMessage(Definition definition) {
        Message xrdHeaderMessage = definition.createMessage();
        xrdHeaderMessage.setQName(new QName("xrdheader"));
        xrdHeaderMessage.addPart(createXRoadHeaderPart(definition, "client"));
        xrdHeaderMessage.addPart(createXRoadHeaderPart(definition, "service"));
        xrdHeaderMessage.addPart(createXRoadHeaderPart(definition, "userId"));
        xrdHeaderMessage.addPart(createXRoadHeaderPart(definition, "id"));
        xrdHeaderMessage.addPart(createXRoadHeaderPart(definition, "protocolVersion"));
        xrdHeaderMessage.setUndefined(false);
        return xrdHeaderMessage;
    }

    private Part createXRoadHeaderPart(Definition definition, String name) {
        Part clientPart = definition.createPart();
        clientPart.setName(name);
        clientPart.setElementName(new QName("http://x-road.eu/xsd/xroad.xsd", name));
        return clientPart;
    }


}
