package kg.gov.tunduk.xroad;

import lombok.Getter;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.mime.Attachment;
import org.springframework.ws.soap.SoapFaultException;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class XRoadClientInterceptor implements ClientInterceptor {

    @Getter
    private Iterator<Attachment> attachments;

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        WebServiceMessage response = messageContext.getResponse();
        if (response instanceof SaajSoapMessage) {
            SaajSoapMessage saajSoapMessage = (SaajSoapMessage) response;
            try {
                SOAPFault fault = saajSoapMessage.getSaajMessage().getSOAPBody().getFault();
                if (fault != null) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    saajSoapMessage.getSaajMessage().writeTo(outputStream);
                } else {
                    this.attachments = saajSoapMessage.getAttachments();
                }
            } catch (SOAPException | IOException e) {
                throw new SoapFaultException(e.getLocalizedMessage());
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException { }
}
