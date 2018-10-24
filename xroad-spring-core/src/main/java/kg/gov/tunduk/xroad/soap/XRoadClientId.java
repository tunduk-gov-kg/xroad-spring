package kg.gov.tunduk.xroad.soap;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XRoadClientIdentifierType", namespace = "http://x-road.eu/xsd/identifiers")
@XmlRootElement(name = "client", namespace = "http://x-road.eu/xsd/xroad.xsd")
public class XRoadClientId {

    @XmlAttribute(name = "objectType", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private ObjectType objectType;

    @XmlElement(name = "xRoadInstance", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String instance;

    @XmlElement(name = "memberClass", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String memberClass;

    @XmlElement(name = "memberCode", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String memberCode;

    @XmlElement(name = "subsystemCode", namespace = "http://x-road.eu/xsd/identifiers")
    private String subSystemCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XRoadClientId that = (XRoadClientId) o;

        if (objectType != that.objectType) return false;
        if (!instance.equals(that.instance)) return false;
        if (!memberClass.equals(that.memberClass)) return false;
        if (!memberCode.equals(that.memberCode)) return false;
        return subSystemCode != null ? subSystemCode.equals(that.subSystemCode) : that.subSystemCode == null;
    }

    @Override
    public int hashCode() {
        int result = objectType.hashCode();
        result = 31 * result + instance.hashCode();
        result = 31 * result + memberClass.hashCode();
        result = 31 * result + memberCode.hashCode();
        result = 31 * result + (subSystemCode != null ? subSystemCode.hashCode() : 0);
        return result;
    }

    public static XRoadClientId from(XRoadServiceId serviceId) {
        XRoadClientId subSystemId = new XRoadClientId();
        subSystemId.setInstance(serviceId.getInstance());
        subSystemId.setMemberClass(serviceId.getMemberClass());
        subSystemId.setMemberCode(serviceId.getMemberCode());
        subSystemId.setSubSystemCode(serviceId.getSubSystemCode());
        return subSystemId;
    }
}
