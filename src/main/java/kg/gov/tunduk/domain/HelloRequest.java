package kg.gov.tunduk.domain;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlType(name = "HelloRequest", namespace = "http://tunduk.gov.kg")
@XmlRootElement(name = "helloRequest", namespace = "http://tunduk.gov.kg")
@XmlAccessorType(XmlAccessType.FIELD)
public class HelloRequest {

    @XmlElement(name = "name", required = true, namespace = "http://tunduk.gov.kg")
    private String name;
}
