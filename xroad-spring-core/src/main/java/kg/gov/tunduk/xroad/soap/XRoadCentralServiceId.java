package kg.gov.tunduk.xroad.soap;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "centralService", namespace = "http://x-road.eu/xsd/xroad.xsd")
@XmlType(name = "XRoadCentralServiceIdentifierType", namespace = "http://x-road.eu/xsd/identifiers")
public class XRoadCentralServiceId {

    @XmlElement(name = "xRoadInstance", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String instance;

    @XmlElement(name = "serviceCode", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String serviceCode;
}

