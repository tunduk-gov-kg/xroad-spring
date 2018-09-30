package kg.gov.tunduk.configuration;

import org.springframework.ws.wsdl.wsdl11.provider.InliningXsdSchemaTypesProvider;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;

public class XRoadXsdSchemaTypesProvider extends InliningXsdSchemaTypesProvider {

    @Override
    public void addTypes(Definition definition) throws WSDLException {
        super.addTypes(definition);
    }
}
