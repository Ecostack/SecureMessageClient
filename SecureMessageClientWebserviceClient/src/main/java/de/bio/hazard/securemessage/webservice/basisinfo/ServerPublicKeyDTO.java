
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
 *         &lt;element name="serverPublicKey" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "serverPublicKey"
})
public class ServerPublicKeyDTO {

    protected byte[] serverPublicKey;

    /**
     * Gets the value of the serverPublicKey property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getServerPublicKey() {
        return serverPublicKey;
    }

    /**
     * Sets the value of the serverPublicKey property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setServerPublicKey(byte[] value) {
        this.serverPublicKey = value;
    }

}
