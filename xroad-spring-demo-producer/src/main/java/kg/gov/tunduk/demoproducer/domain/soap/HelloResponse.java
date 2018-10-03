package kg.gov.tunduk.demoproducer.domain.soap;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HelloResponse", namespace = "http://tunduk.gov.kg")
@XmlRootElement(name = "helloResponse", namespace = "http://tunduk.gov.kg")
public class HelloResponse {

    @XmlElement(name = "greeting", namespace = "http://tunduk.gov.kg", required = true)
    private String greeting;
}