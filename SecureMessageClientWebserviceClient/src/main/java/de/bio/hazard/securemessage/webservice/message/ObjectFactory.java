
package de.bio.hazard.securemessage.webservice.message;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.bio.hazard.securemessage.webservice.message package. 
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

    private final static QName _AddMessageResponse_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "addMessageResponse");
    private final static QName _AddMessage_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "addMessage");
    private final static QName _EncryptionExceptionBiohazard_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "EncryptionExceptionBiohazard");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.bio.hazard.securemessage.webservice.message
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddMessageResponse }
     * 
     */
    public AddMessageResponse createAddMessageResponse() {
        return new AddMessageResponse();
    }

    /**
     * Create an instance of {@link AddMessage }
     * 
     */
    public AddMessage createAddMessage() {
        return new AddMessage();
    }

    /**
     * Create an instance of {@link EncryptionExceptionBiohazard }
     * 
     */
    public EncryptionExceptionBiohazard createEncryptionExceptionBiohazard() {
        return new EncryptionExceptionBiohazard();
    }

    /**
     * Create an instance of {@link MessageReceiverWebserviceDTO }
     * 
     */
    public MessageReceiverWebserviceDTO createMessageReceiverWebserviceDTO() {
        return new MessageReceiverWebserviceDTO();
    }

    /**
     * Create an instance of {@link MessageWebserviceDTO }
     * 
     */
    public MessageWebserviceDTO createMessageWebserviceDTO() {
        return new MessageWebserviceDTO();
    }

    /**
     * Create an instance of {@link MessageContentWebserviceDTO }
     * 
     */
    public MessageContentWebserviceDTO createMessageContentWebserviceDTO() {
        return new MessageContentWebserviceDTO();
    }

    /**
     * Create an instance of {@link MessageContentKeyWebserviceDTO }
     * 
     */
    public MessageContentKeyWebserviceDTO createMessageContentKeyWebserviceDTO() {
        return new MessageContentKeyWebserviceDTO();
    }

    /**
     * Create an instance of {@link AbstractDTO }
     * 
     */
    public AbstractDTO createAbstractDTO() {
        return new AbstractDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "addMessageResponse")
    public JAXBElement<AddMessageResponse> createAddMessageResponse(AddMessageResponse value) {
        return new JAXBElement<AddMessageResponse>(_AddMessageResponse_QNAME, AddMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "addMessage")
    public JAXBElement<AddMessage> createAddMessage(AddMessage value) {
        return new JAXBElement<AddMessage>(_AddMessage_QNAME, AddMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptionExceptionBiohazard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "EncryptionExceptionBiohazard")
    public JAXBElement<EncryptionExceptionBiohazard> createEncryptionExceptionBiohazard(EncryptionExceptionBiohazard value) {
        return new JAXBElement<EncryptionExceptionBiohazard>(_EncryptionExceptionBiohazard_QNAME, EncryptionExceptionBiohazard.class, null, value);
    }

}
