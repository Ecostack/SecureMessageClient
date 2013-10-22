
package de.bio.hazard.securemessage.webservice.user;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userListWebserviceReturnDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userListWebserviceReturnDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listItemWebserviceReturnDTOs" type="{http://webservice.securemessage.hazard.bio.de/}userListItemWebserviceReturnDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userListWebserviceReturnDTO", propOrder = {
    "listItemWebserviceReturnDTOs"
})
public class UserListWebserviceReturnDTO {

    @XmlElement(nillable = true)
    protected List<UserListItemWebserviceReturnDTO> listItemWebserviceReturnDTOs;

    /**
     * Gets the value of the listItemWebserviceReturnDTOs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listItemWebserviceReturnDTOs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListItemWebserviceReturnDTOs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserListItemWebserviceReturnDTO }
     * 
     * 
     */
    public List<UserListItemWebserviceReturnDTO> getListItemWebserviceReturnDTOs() {
        if (listItemWebserviceReturnDTOs == null) {
            listItemWebserviceReturnDTOs = new ArrayList<UserListItemWebserviceReturnDTO>();
        }
        return this.listItemWebserviceReturnDTOs;
    }

}
