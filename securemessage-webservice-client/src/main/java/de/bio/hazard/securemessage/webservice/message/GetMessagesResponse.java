
package de.bio.hazard.securemessage.webservice.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMessagesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMessagesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservice.securemessage.hazard.bio.de/}requestMessageWebserviceReturnDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessagesResponse", propOrder = {
    "_return"
})
public class GetMessagesResponse {

    @XmlElement(name = "return")
    protected RequestMessageWebserviceReturnDTO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link RequestMessageWebserviceReturnDTO }
     *     
     */
    public RequestMessageWebserviceReturnDTO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestMessageWebserviceReturnDTO }
     *     
     */
    public void setReturn(RequestMessageWebserviceReturnDTO value) {
        this._return = value;
    }

}
