
package de.bio.hazard.securemessage.webservice.authentication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for authenticationStepTwoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="authenticationStepTwoDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.securemessage.hazard.bio.de/}abstractDTO">
 *       &lt;sequence>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handshakeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="randomHashedValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authenticationStepTwoDTO", propOrder = {
    "date",
    "handshakeId",
    "randomHashedValue"
})
public class AuthenticationStepTwoDTO
    extends AbstractDTO
{

    protected String date;
    protected String handshakeId;
    protected String randomHashedValue;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the handshakeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandshakeId() {
        return handshakeId;
    }

    /**
     * Sets the value of the handshakeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandshakeId(String value) {
        this.handshakeId = value;
    }

    /**
     * Gets the value of the randomHashedValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRandomHashedValue() {
        return randomHashedValue;
    }

    /**
     * Sets the value of the randomHashedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRandomHashedValue(String value) {
        this.randomHashedValue = value;
    }

}
