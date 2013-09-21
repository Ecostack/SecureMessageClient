
package de.bio.hazard.securemessage.webservice.authentication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for newDeviceWebserviceDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="newDeviceWebserviceDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.securemessage.hazard.bio.de/}abstractDTO">
 *       &lt;sequence>
 *         &lt;element name="devicename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publicKeyForDevice" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "newDeviceWebserviceDTO", propOrder = {
    "devicename",
    "password",
    "publicKeyForDevice",
    "username"
})
public class NewDeviceWebserviceDTO
    extends AbstractDTO
{

    protected String devicename;
    protected String password;
    protected String publicKeyForDevice;
    protected String username;

    /**
     * Gets the value of the devicename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevicename() {
        return devicename;
    }

    /**
     * Sets the value of the devicename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevicename(String value) {
        this.devicename = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the publicKeyForDevice property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public String getPublicKeyForDevice() {
        return publicKeyForDevice;
    }

    /**
     * Sets the value of the publicKeyForDevice property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPublicKeyForDevice(String value) {
        this.publicKeyForDevice = value;
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
