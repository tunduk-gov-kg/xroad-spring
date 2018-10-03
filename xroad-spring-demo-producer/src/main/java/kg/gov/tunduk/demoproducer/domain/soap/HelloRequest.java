package kg.gov.tunduk.demoproducer.domain.soap;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HelloRequest", namespace = "http://tunduk.gov.kg")
@XmlRootElement(name = "helloRequest", namespace = "http://tunduk.gov.kg")
public class HelloRequest {

    @XmlElement(name = "name", namespace = "http://tunduk.gov.kg", required = true)
    private String name;
}
