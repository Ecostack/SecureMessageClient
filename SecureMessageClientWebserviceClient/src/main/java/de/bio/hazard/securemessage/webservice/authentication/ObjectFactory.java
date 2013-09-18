
package de.bio.hazard.securemessage.webservice.authentication;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.bio.hazard.securemessage.webservice.authentication package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddNewDeviceResponse_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "addNewDeviceResponse");
    private final static QName _AddNewUser_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "addNewUser");
    private final static QName _AddNewUserResponse_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "addNewUserResponse");
    private final static QName _AddNewDevice_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "addNewDevice");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.bio.hazard.securemessage.webservice.authentication
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddNewDeviceResponse }
     * 
     */
    public AddNewDeviceResponse createAddNewDeviceResponse() {
        return new AddNewDeviceResponse();
    }

    /**
     * Create an instance of {@link AddNewUser }
     * 
     */
    public AddNewUser createAddNewUser() {
        return new AddNewUser();
    }

    /**
     * Create an instance of {@link AddNewDevice }
     * 
     */
    public AddNewDevice createAddNewDevice() {
        return new AddNewDevice();
    }

    /**
     * Create an instance of {@link AddNewUserResponse }
     * 
     */
    public AddNewUserResponse createAddNewUserResponse() {
        return new AddNewUserResponse();
    }

    /**
     * Create an instance of {@link NewDeviceWebserviceDTO }
     * 
     */
    public NewDeviceWebserviceDTO createNewDeviceWebserviceDTO() {
        return new NewDeviceWebserviceDTO();
    }

    /**
     * Create an instance of {@link NewUserWebserviceDTO }
     * 
     */
    public NewUserWebserviceDTO createNewUserWebserviceDTO() {
        return new NewUserWebserviceDTO();
    }

    /**
     * Create an instance of {@link AbstractDTO }
     * 
     */
    public AbstractDTO createAbstractDTO() {
        return new AbstractDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewDeviceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "addNewDeviceResponse")
    public JAXBElement<AddNewDeviceResponse> createAddNewDeviceResponse(AddNewDeviceResponse value) {
        return new JAXBElement<AddNewDeviceResponse>(_AddNewDeviceResponse_QNAME, AddNewDeviceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "addNewUser")
    public JAXBElement<AddNewUser> createAddNewUser(AddNewUser value) {
        return new JAXBElement<AddNewUser>(_AddNewUser_QNAME, AddNewUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "addNewUserResponse")
    public JAXBElement<AddNewUserResponse> createAddNewUserResponse(AddNewUserResponse value) {
        return new JAXBElement<AddNewUserResponse>(_AddNewUserResponse_QNAME, AddNewUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewDevice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "addNewDevice")
    public JAXBElement<AddNewDevice> createAddNewDevice(AddNewDevice value) {
        return new JAXBElement<AddNewDevice>(_AddNewDevice_QNAME, AddNewDevice.class, null, value);
    }

}
