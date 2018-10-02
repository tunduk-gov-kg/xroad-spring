package kg.gov.tunduk.xroad;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;
import org.springframework.ws.wsdl.wsdl11.ProviderBasedWsdl4jDefinition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.provider.Soap11Provider;
import org.springframework.ws.wsdl.wsdl11.provider.SuffixBasedMessagesProvider;
import org.springframework.ws.wsdl.wsdl11.provider.SuffixBasedPortTypesProvider;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;

import javax.xml.transform.Source;
import java.util.Properties;

public class XRoadWsdlDefinition implements Wsdl11Definition, InitializingBean {

    private final XRoadXsdSchemaTypesProvider typesProvider = new XRoadXsdSchemaTypesProvider();

    private final SuffixBasedMessagesProvider messagesProvider = new XRoadMessagesProvider();

    private final SuffixBasedPortTypesProvider portTypesProvider = new SuffixBasedPortTypesProvider();

    private final Soap11Provider soapProvider = new XRoadSoapProvider();

    private final ProviderBasedWsdl4jDefinition delegate = new ProviderBasedWsdl4jDefinition();

    private String serviceName;

    public XRoadWsdlDefinition() {
        delegate.setTypesProvider(typesProvider);
        delegate.setMessagesProvider(messagesProvider);
        delegate.setPortTypesProvider(portTypesProvider);
        delegate.setBindingsProvider(soapProvider);
        delegate.setServicesProvider(soapProvider);
    }

    public void setTargetNamespace(String targetNamespace) {
        delegate.setTargetNamespace(targetNamespace);
    }

    public void setSchema(XsdSchema schema) {
        typesProvider.setSchema(schema);
    }

    public void setSchemaCollection(XsdSchemaCollection schemaCollection) {
        typesProvider.setSchemaCollection(schemaCollection);
    }

    public void setPortTypeName(String portTypeName) {
        portTypesProvider.setPortTypeName(portTypeName);
    }

    public void setRequestSuffix(String requestSuffix) {
        portTypesProvider.setRequestSuffix(requestSuffix);
        messagesProvider.setRequestSuffix(requestSuffix);
    }

    public void setResponseSuffix(String responseSuffix) {
        portTypesProvider.setResponseSuffix(responseSuffix);
        messagesProvider.setResponseSuffix(responseSuffix);
    }

    public void setFaultSuffix(String faultSuffix) {
        portTypesProvider.setFaultSuffix(faultSuffix);
        messagesProvider.setFaultSuffix(faultSuffix);
    }

    public void setSoapActions(Properties soapActions) {
        soapProvider.setSoapActions(soapActions);
    }

    public void setTransportUri(String transportUri) {
        soapProvider.setTransportUri(transportUri);
    }

    public void setLocationUri(String locationUri) {
        soapProvider.setLocationUri(locationUri);
    }

    public void setServiceName(String serviceName) {
        soapProvider.setServiceName(serviceName);
        this.serviceName = serviceName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        boolean hasNoTargetNamespace = !StringUtils.hasText(delegate.getTargetNamespace());

        if (hasNoTargetNamespace && typesProvider.getSchemaCollection() != null &&
                typesProvider.getSchemaCollection().getXsdSchemas().length > 0) {
            XsdSchema schema = typesProvider.getSchemaCollection().getXsdSchemas()[0];
            setTargetNamespace(schema.getTargetNamespace());
        }
        if (!StringUtils.hasText(serviceName) && StringUtils.hasText(portTypesProvider.getPortTypeName())) {
            soapProvider.setServiceName(portTypesProvider.getPortTypeName() + "Service");
        }
        delegate.afterPropertiesSet();
    }

    @Override
    public Source getSource() {
        return delegate.getSource();
    }
}
