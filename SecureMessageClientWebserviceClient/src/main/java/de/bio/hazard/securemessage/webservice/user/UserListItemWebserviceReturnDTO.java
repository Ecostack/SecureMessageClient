
package de.bio.hazard.securemessage.webservice.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for userListItemWebserviceReturnDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userListItemWebserviceReturnDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lastLoginAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="publicKeyForMessaging" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
@XmlType(name = "userListItemWebserviceReturnDTO", propOrder = {
    "lastLoginAt",
    "publicKeyForMessaging",
    "username"
})
public class UserListItemWebserviceReturnDTO {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastLoginAt;
    protected byte[] publicKeyForMessaging;
    protected String username;

    /**
     * Gets the value of the lastLoginAt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastLoginAt() {
        return lastLoginAt;
    }

    /**
     * Sets the value of the lastLoginAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastLoginAt(XMLGregorianCalendar value) {
        this.lastLoginAt = value;
    }

    /**
     * Gets the value of the publicKeyForMessaging property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPublicKeyForMessaging() {
        return publicKeyForMessaging;
    }

    /**
     * Sets the value of the publicKeyForMessaging property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPublicKeyForMessaging(byte[] value) {
        this.publicKeyForMessaging = value;
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
