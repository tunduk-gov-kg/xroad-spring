package kg.gov.tunduk.xroad.soap;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XRoadClientIdentifierType", namespace = "http://x-road.eu/xsd/identifiers")
@XmlRootElement(name = "client", namespace = "http://x-road.eu/xsd/xroad.xsd")
public class XRoadSubSystemId {

    @XmlElement(name = "xRoadInstance", namespace = "http://x-road.eu/xsd/identifiers")
    private String instance;

    @XmlElement(name = "memberClass", namespace = "http://x-road.eu/xsd/identifiers")
    private String memberClass;

    @XmlElement(name = "memberCode", namespace = "http://x-road.eu/xsd/identifiers")
    private String memberCode;

    @XmlElement(name = "subsystemCode", namespace = "http://x-road.eu/xsd/identifiers")
    private String subSystemCode;
}
