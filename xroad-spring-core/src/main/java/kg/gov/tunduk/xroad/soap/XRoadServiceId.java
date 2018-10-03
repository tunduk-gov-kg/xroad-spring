package kg.gov.tunduk.xroad.soap;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XRoadServiceIdentifierType", namespace = "http://x-road.eu/xsd/identifiers")
@XmlRootElement(name = "service", namespace = "http://x-road.eu/xsd/xroad.xsd")
public class XRoadServiceId {

    @XmlElement(name = "xRoadInstance", namespace = "http://x-road.eu/xsd/identifiers")
    private String instance;

    @XmlElement(name = "memberClass", namespace = "http://x-road.eu/xsd/identifiers")
    private String memberClass;

    @XmlElement(name = "memberCode", namespace = "http://x-road.eu/xsd/identifiers")
    private String memberCode;

    @XmlElement(name = "subsystemCode", namespace = "http://x-road.eu/xsd/identifiers")
    private String subSystemCode;

    @XmlElement(name = "serviceCode", namespace = "http://x-road.eu/xsd/identifiers")
    private String serviceCode;

    @XmlElement(name = "serviceVersion", namespace = "http://x-road.eu/xsd/identifiers")
    private String serviceVersion;
}
