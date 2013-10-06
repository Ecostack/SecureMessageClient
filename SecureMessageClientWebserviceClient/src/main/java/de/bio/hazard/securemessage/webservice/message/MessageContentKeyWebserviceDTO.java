
package de.bio.hazard.securemessage.webservice.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for messageContentKeyWebserviceDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="messageContentKeyWebserviceDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="symmetricEncryptionKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "messageContentKeyWebserviceDTO", propOrder = {
    "symmetricEncryptionKey",
    "username"
})
public class MessageContentKeyWebserviceDTO {

    protected String symmetricEncryptionKey;
    protected String username;

    /**
     * Gets the value of the symmetricEncryptionKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymmetricEncryptionKey() {
        return symmetricEncryptionKey;
    }

    /**
     * Sets the value of the symmetricEncryptionKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymmetricEncryptionKey(String value) {
        this.symmetricEncryptionKey = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

}
