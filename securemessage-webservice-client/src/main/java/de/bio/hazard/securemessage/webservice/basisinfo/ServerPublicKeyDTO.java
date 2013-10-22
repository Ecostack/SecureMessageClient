
package de.bio.hazard.securemessage.webservice.basisinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serverPublicKeyDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serverPublicKeyDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serverPublicKeyAsBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serverPublicKeyDTO", propOrder = {
    "serverPublicKeyAsBase64"
})
public class ServerPublicKeyDTO {

    protected String serverPublicKeyAsBase64;

    /**
     * Gets the value of the serverPublicKeyAsBase64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerPublicKeyAsBase64() {
        return serverPublicKeyAsBase64;
    }

    /**
     * Sets the value of the serverPublicKeyAsBase64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerPublicKeyAsBase64(String value) {
        this.serverPublicKeyAsBase64 = value;
    }

}
