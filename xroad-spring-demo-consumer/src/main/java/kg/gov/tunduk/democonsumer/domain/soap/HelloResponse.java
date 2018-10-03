
package kg.gov.tunduk.democonsumer.domain.soap;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for HelloResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="HelloResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="greeting" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HelloResponse", namespace = "http://tunduk.gov.kg", propOrder = {
        "greeting"
})
@XmlRootElement(name = "helloResponse", namespace = "http://tunduk.gov.kg")
public class HelloResponse {

    @XmlElement(required = true)
    protected String greeting;

    /**
     * Gets the value of the greeting property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * Sets the value of the greeting property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGreeting(String value) {
        this.greeting = value;
    }

}
