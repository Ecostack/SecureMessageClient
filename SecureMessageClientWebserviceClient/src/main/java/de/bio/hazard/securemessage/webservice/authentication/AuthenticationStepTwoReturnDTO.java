
package de.bio.hazard.securemessage.webservice.authentication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for authenticationStepTwoReturnDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="authenticationStepTwoReturnDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.securemessage.hazard.bio.de/}abstractDTO">
 *       &lt;sequence>
 *         &lt;element name="tokenId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authenticationStepTwoReturnDTO", propOrder = {
    "tokenId"
})
public class AuthenticationStepTwoReturnDTO
    extends AbstractDTO
{

    protected String tokenId;

    /**
     * Gets the value of the tokenId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * Sets the value of the tokenId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTokenId(String value) {
        this.tokenId = value;
    }

}
