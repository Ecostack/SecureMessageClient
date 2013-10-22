
package de.bio.hazard.securemessage.webservice.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMessages complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMessages">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://webservice.securemessage.hazard.bio.de/}requestMessageWebserviceDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessages", propOrder = {
    "arg0"
})
public class GetMessages {

    protected RequestMessageWebserviceDTO arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link RequestMessageWebserviceDTO }
     *     
     */
    public RequestMessageWebserviceDTO getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestMessageWebserviceDTO }
     *     
     */
    public void setArg0(RequestMessageWebserviceDTO value) {
        this.arg0 = value;
    }

}
