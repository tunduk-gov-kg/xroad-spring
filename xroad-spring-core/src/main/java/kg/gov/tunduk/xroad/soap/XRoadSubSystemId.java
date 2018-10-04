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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XRoadSubSystemId that = (XRoadSubSystemId) o;

        if (!instance.equals(that.instance)) return false;
        if (!memberClass.equals(that.memberClass)) return false;
        if (!memberCode.equals(that.memberCode)) return false;
        return subSystemCode.equals(that.subSystemCode);
    }

    @Override
    public int hashCode() {
        int result = instance.hashCode();
        result = 31 * result + memberClass.hashCode();
        result = 31 * result + memberCode.hashCode();
        result = 31 * result + subSystemCode.hashCode();
        return result;
    }

    public static XRoadSubSystemId from(XRoadServiceId serviceId) {
        XRoadSubSystemId subSystemId = new XRoadSubSystemId();
        subSystemId.setInstance(serviceId.getInstance());
        subSystemId.setMemberClass(serviceId.getMemberClass());
        subSystemId.setMemberCode(serviceId.getMemberCode());
        subSystemId.setSubSystemCode(serviceId.getSubSystemCode());
        return subSystemId;
    }
}
