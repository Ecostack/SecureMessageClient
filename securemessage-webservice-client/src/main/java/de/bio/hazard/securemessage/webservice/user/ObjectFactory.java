
package de.bio.hazard.securemessage.webservice.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.bio.hazard.securemessage.webservice.user package. 
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

    private final static QName _GetPublicKeyByUsernameResponse_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "getPublicKeyByUsernameResponse");
    private final static QName _GetUsersResponse_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "getUsersResponse");
    private final static QName _UserNotFoundException_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "UserNotFoundException");
    private final static QName _GetPublicKeyByUsername_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "getPublicKeyByUsername");
    private final static QName _GetUsers_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "getUsers");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.bio.hazard.securemessage.webservice.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link GetPublicKeyByUsernameResponse }
     * 
     */
    public GetPublicKeyByUsernameResponse createGetPublicKeyByUsernameResponse() {
        return new GetPublicKeyByUsernameResponse();
    }

    /**
     * Create an instance of {@link UserNotFoundException }
     * 
     */
    public UserNotFoundException createUserNotFoundException() {
        return new UserNotFoundException();
    }

    /**
     * Create an instance of {@link GetPublicKeyByUsername }
     * 
     */
    public GetPublicKeyByUsername createGetPublicKeyByUsername() {
        return new GetPublicKeyByUsername();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link UserListItemWebserviceReturnDTO }
     * 
     */
    public UserListItemWebserviceReturnDTO createUserListItemWebserviceReturnDTO() {
        return new UserListItemWebserviceReturnDTO();
    }

    /**
     * Create an instance of {@link UserWebserviceDTO }
     * 
     */
    public UserWebserviceDTO createUserWebserviceDTO() {
        return new UserWebserviceDTO();
    }

    /**
     * Create an instance of {@link UserListWebserviceDTO }
     * 
     */
    public UserListWebserviceDTO createUserListWebserviceDTO() {
        return new UserListWebserviceDTO();
    }

    /**
     * Create an instance of {@link UserWebserviceReturnDTO }
     * 
     */
    public UserWebserviceReturnDTO createUserWebserviceReturnDTO() {
        return new UserWebserviceReturnDTO();
    }

    /**
     * Create an instance of {@link UserListWebserviceReturnDTO }
     * 
     */
    public UserListWebserviceReturnDTO createUserListWebserviceReturnDTO() {
        return new UserListWebserviceReturnDTO();
    }

    /**
     * Create an instance of {@link AbstractDTO }
     * 
     */
    public AbstractDTO createAbstractDTO() {
        return new AbstractDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPublicKeyByUsernameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "getPublicKeyByUsernameResponse")
    public JAXBElement<GetPublicKeyByUsernameResponse> createGetPublicKeyByUsernameResponse(GetPublicKeyByUsernameResponse value) {
        return new JAXBElement<GetPublicKeyByUsernameResponse>(_GetPublicKeyByUsernameResponse_QNAME, GetPublicKeyByUsernameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "UserNotFoundException")
    public JAXBElement<UserNotFoundException> createUserNotFoundException(UserNotFoundException value) {
        return new JAXBElement<UserNotFoundException>(_UserNotFoundException_QNAME, UserNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPublicKeyByUsername }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "getPublicKeyByUsername")
    public JAXBElement<GetPublicKeyByUsername> createGetPublicKeyByUsername(GetPublicKeyByUsername value) {
        return new JAXBElement<GetPublicKeyByUsername>(_GetPublicKeyByUsername_QNAME, GetPublicKeyByUsername.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

}
