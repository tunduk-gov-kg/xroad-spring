package kg.gov.tunduk.xroad;

import com.ibm.wsdl.ImportImpl;
import org.springframework.ws.wsdl.wsdl11.provider.InliningXsdSchemaTypesProvider;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;

public class XRoadXsdSchemaTypesProvider extends InliningXsdSchemaTypesProvider {

    @Override
    public void addTypes(Definition definition) throws WSDLException {
        // Importing xroad.xsd
        // <xs:import namespace="http://x-road.eu/xsd/xroad.xsd" schemaLocation="http://x-road.eu/xsd/xroad.xsd"/>
        ImportImpl xRoadXsdImport = new ImportImpl();
        xRoadXsdImport.setLocationURI("http://x-road.eu/xsd/xroad.xsd");
        xRoadXsdImport.setNamespaceURI("http://x-road.eu/xsd/xroad.xsd");
        definition.addImport(xRoadXsdImport);
        super.addTypes(definition);
    }
}
