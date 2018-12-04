package kg.gov.tunduk.xroad.soap;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "securityServer", namespace = "http://x-road.eu/xsd/xroad.xsd")
@XmlType(name = "XRoadSecurityServerIdentifierType", namespace = "http://x-road.eu/xsd/identifiers")
public class XRoadSecurityServerIdentifierType {

    @XmlAttribute(name = "objectType", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private XRoadObjectType objectType = XRoadObjectType.SERVER;

    @XmlElement(name = "xRoadInstance", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String instance;

    @XmlElement(name = "memberClass", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String memberClass;

    @XmlElement(name = "memberCode", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String memberCode;

    @XmlElement(name = "serverCode", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String serverCode;
}
