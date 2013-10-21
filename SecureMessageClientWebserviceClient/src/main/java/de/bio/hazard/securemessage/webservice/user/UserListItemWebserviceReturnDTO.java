
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
 *         &lt;element name="publicKeyForMessagingAsBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "publicKeyForMessagingAsBase64",
    "username"
})
public class UserListItemWebserviceReturnDTO {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastLoginAt;
    protected String publicKeyForMessagingAsBase64;
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
     * Gets the value of the publicKeyForMessagingAsBase64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicKeyForMessagingAsBase64() {
        return publicKeyForMessagingAsBase64;
    }

    /**
     * Sets the value of the publicKeyForMessagingAsBase64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicKeyForMessagingAsBase64(String value) {
        this.publicKeyForMessagingAsBase64 = value;
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
