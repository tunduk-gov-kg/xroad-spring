package kg.gov.tunduk.xroad;

import lombok.val;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.MethodEndpoint;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.server.SoapEndpointInterceptor;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.util.Iterator;

public class XRoadEndpointInterceptor implements SoapEndpointInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws JAXBException {
        if (endpoint instanceof MethodEndpoint) {
            val methodEndpoint = (MethodEndpoint) endpoint;
            Object endpointBean = methodEndpoint.getBean();
            if (endpointBean instanceof XRoadEndpoint) {
                XRoadEndpoint xRoadEndpoint = (XRoadEndpoint) endpointBean;
                SoapHeader soapHeader = fetchSoapHeader(messageContext.getRequest());
                xRoadEndpoint.initialize(soapHeader);
            }
        }
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws TransformerException {
        copyHeadersToResponse(messageContext.getRequest(), messageContext.getResponse());
        return true;
    }


    private SoapHeader fetchSoapHeader(WebServiceMessage message) {
        return ((SoapMessage) message).getEnvelope().getHeader();
    }


    private void copyHeadersToResponse(WebServiceMessage request, WebServiceMessage response) throws TransformerException {
        SoapHeader requestHeader = fetchSoapHeader(request);
        SoapHeader responseHeader = fetchSoapHeader(response);

        val transformer = TransformerFactory.newInstance().newTransformer();

        Iterator<SoapHeaderElement> headerElements = requestHeader.examineAllHeaderElements();
        while (headerElements.hasNext()) {
            SoapHeaderElement headerElement = headerElements.next();
            transformer.transform(headerElement.getSource(), responseHeader.getResult());
        }
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
    }

    @Override
    public boolean understands(SoapHeaderElement header) {
        return true;
    }
}
