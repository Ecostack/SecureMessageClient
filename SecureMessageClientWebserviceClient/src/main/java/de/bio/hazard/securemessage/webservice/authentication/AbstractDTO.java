
package de.bio.hazard.securemessage.webservice.authentication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for abstractDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="abstractDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="symEncryptionKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abstractDTO", propOrder = {
    "symEncryptionKey"
})
@XmlSeeAlso({
    AuthenticationStepTwoDTO.class,
    NewUserWebserviceDTO.class,
    AuthenticationStepOneReturnDTO.class,
    NewDeviceWebserviceDTO.class,
    AuthenticationStepOneDTO.class,
    AuthenticationStepTwoReturnDTO.class
})
public class AbstractDTO {

    protected String symEncryptionKey;

    /**
     * Gets the value of the symEncryptionKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymEncryptionKey() {
        return symEncryptionKey;
    }

    /**
     * Sets the value of the symEncryptionKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymEncryptionKey(String value) {
        this.symEncryptionKey = value;
    }

}
