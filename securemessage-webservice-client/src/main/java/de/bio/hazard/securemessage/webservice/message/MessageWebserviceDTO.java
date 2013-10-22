
package de.bio.hazard.securemessage.webservice.message;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for messageWebserviceDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="messageWebserviceDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.securemessage.hazard.bio.de/}abstractDTO">
 *       &lt;sequence>
 *         &lt;element name="content" type="{http://webservice.securemessage.hazard.bio.de/}messageContentWebserviceDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="receiver" type="{http://webservice.securemessage.hazard.bio.de/}messageReceiverWebserviceDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "messageWebserviceDTO", propOrder = {
    "content",
    "receiver"
})
public class MessageWebserviceDTO
    extends AbstractDTO
{

    @XmlElement(nillable = true)
    protected List<MessageContentWebserviceDTO> content;
    @XmlElement(nillable = true)
    protected List<MessageReceiverWebserviceDTO> receiver;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageContentWebserviceDTO }
     * 
     * 
     */
    public List<MessageContentWebserviceDTO> getContent() {
        if (content == null) {
            content = new ArrayList<MessageContentWebserviceDTO>();
        }
        return this.content;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the receiver property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReceiver().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageReceiverWebserviceDTO }
     * 
     * 
     */
    public List<MessageReceiverWebserviceDTO> getReceiver() {
        if (receiver == null) {
            receiver = new ArrayList<MessageReceiverWebserviceDTO>();
        }
        return this.receiver;
    }

}
