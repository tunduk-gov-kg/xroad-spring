package kg.gov.tunduk.configuration;

import org.springframework.ws.wsdl.wsdl11.provider.Soap11Provider;

import javax.wsdl.*;
import javax.wsdl.extensions.soap.SOAPHeader;
import javax.xml.namespace.QName;

public class XRoadSoapProvider extends Soap11Provider {

    @Override
    protected void populateBindingInput(Definition definition, BindingInput bindingInput, Input input) throws WSDLException {
        super.populateBindingInput(definition, bindingInput, input);
        bindingInput.addExtensibilityElement(createSoapHeader(definition, "client"));
        bindingInput.addExtensibilityElement(createSoapHeader(definition, "service"));
        bindingInput.addExtensibilityElement(createSoapHeader(definition, "userId"));
        bindingInput.addExtensibilityElement(createSoapHeader(definition, "id"));
        bindingInput.addExtensibilityElement(createSoapHeader(definition, "protocolVersion"));
    }

    @Override
    protected void populateBindingOutput(Definition definition, BindingOutput bindingOutput, Output output) throws WSDLException {
        super.populateBindingOutput(definition, bindingOutput, output);
        bindingOutput.addExtensibilityElement(createSoapHeader(definition, "client"));
        bindingOutput.addExtensibilityElement(createSoapHeader(definition, "service"));
        bindingOutput.addExtensibilityElement(createSoapHeader(definition, "userId"));
        bindingOutput.addExtensibilityElement(createSoapHeader(definition, "id"));
        bindingOutput.addExtensibilityElement(createSoapHeader(definition, "protocolVersion"));
    }

    private SOAPHeader createSoapHeader(Definition definition, String name) throws WSDLException {
        SOAPHeader soapHeader = (SOAPHeader) definition.getExtensionRegistry()
                .createExtension(BindingInput.class, new QName(SOAP_11_NAMESPACE_URI, "header"));
        soapHeader.setUse("literal");
        soapHeader.setPart(name);
        soapHeader.setMessage(new QName(definition.getTargetNamespace(), "xrdheader"));
        return soapHeader;
    }
}
