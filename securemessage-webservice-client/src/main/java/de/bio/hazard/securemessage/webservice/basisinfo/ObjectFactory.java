
package de.bio.hazard.securemessage.webservice.basisinfo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.bio.hazard.securemessage.webservice.basisinfo package. 
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

    private final static QName _GetServerPublicKey_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "getServerPublicKey");
    private final static QName _GetServerPublicKeyResponse_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "getServerPublicKeyResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.bio.hazard.securemessage.webservice.basisinfo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetServerPublicKeyResponse }
     * 
     */
    public GetServerPublicKeyResponse createGetServerPublicKeyResponse() {
        return new GetServerPublicKeyResponse();
    }

    /**
     * Create an instance of {@link GetServerPublicKey }
     * 
     */
    public GetServerPublicKey createGetServerPublicKey() {
        return new GetServerPublicKey();
    }

    /**
     * Create an instance of {@link ServerPublicKeyDTO }
     * 
     */
    public ServerPublicKeyDTO createServerPublicKeyDTO() {
        return new ServerPublicKeyDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerPublicKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "getServerPublicKey")
    public JAXBElement<GetServerPublicKey> createGetServerPublicKey(GetServerPublicKey value) {
        return new JAXBElement<GetServerPublicKey>(_GetServerPublicKey_QNAME, GetServerPublicKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerPublicKeyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.securemessage.hazard.bio.de/", name = "getServerPublicKeyResponse")
    public JAXBElement<GetServerPublicKeyResponse> createGetServerPublicKeyResponse(GetServerPublicKeyResponse value) {
        return new JAXBElement<GetServerPublicKeyResponse>(_GetServerPublicKeyResponse_QNAME, GetServerPublicKeyResponse.class, null, value);
    }

}
