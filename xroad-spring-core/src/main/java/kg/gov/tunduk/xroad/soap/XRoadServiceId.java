package kg.gov.tunduk.xroad.soap;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XRoadServiceIdentifierType", namespace = "http://x-road.eu/xsd/identifiers")
@XmlRootElement(name = "service", namespace = "http://x-road.eu/xsd/xroad.xsd")
public class XRoadServiceId {

    @XmlAttribute(name = "objectType", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private ObjectType objectType = ObjectType.SERVICE;

    @XmlElement(name = "xRoadInstance", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String instance;

    @XmlElement(name = "memberClass", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String memberClass;

    @XmlElement(name = "memberCode", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String memberCode;

    @XmlElement(name = "subsystemCode", namespace = "http://x-road.eu/xsd/identifiers")
    private String subSystemCode;

    @XmlElement(name = "serviceCode", required = true, namespace = "http://x-road.eu/xsd/identifiers")
    private String serviceCode;

    @XmlElement(name = "serviceVersion", namespace = "http://x-road.eu/xsd/identifiers")
    private String serviceVersion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XRoadServiceId that = (XRoadServiceId) o;

        if (!instance.equals(that.instance)) return false;
        if (!memberClass.equals(that.memberClass)) return false;
        if (!memberCode.equals(that.memberCode)) return false;
        if (subSystemCode != null ? !subSystemCode.equals(that.subSystemCode) : that.subSystemCode != null)
            return false;
        if (!serviceCode.equals(that.serviceCode)) return false;
        return serviceVersion != null ? serviceVersion.equals(that.serviceVersion) : that.serviceVersion == null;
    }

    @Override
    public int hashCode() {
        int result = instance.hashCode();
        result = 31 * result + memberClass.hashCode();
        result = 31 * result + memberCode.hashCode();
        result = 31 * result + (subSystemCode != null ? subSystemCode.hashCode() : 0);
        result = 31 * result + serviceCode.hashCode();
        result = 31 * result + (serviceVersion != null ? serviceVersion.hashCode() : 0);
        return result;
    }
}
