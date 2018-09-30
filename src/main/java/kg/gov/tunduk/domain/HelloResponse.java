package kg.gov.tunduk.domain;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlType(name = "HelloResponse", namespace = "http://tunduk.gov.kg")
@XmlRootElement(name = "helloResponse", namespace = "http://tunduk.gov.kg")
@XmlAccessorType(XmlAccessType.FIELD)
public class HelloResponse {

    @XmlElement(name = "result", required = true, namespace = "http://tunduk.gov.kg")
    private String result;
}
