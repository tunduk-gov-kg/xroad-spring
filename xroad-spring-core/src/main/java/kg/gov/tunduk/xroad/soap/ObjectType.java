package kg.gov.tunduk.xroad.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "XRoadObjectType", namespace = "http://x-road.eu/xsd/identifiers")
public enum ObjectType {
    MEMBER,
    SUBSYSTEM,
    SERVER,
    GLOBALGROUP,
    LOCALGROUP,
    SECURITYCATEGORY,
    SERVICE,
    CENTRALSERVICE
}
