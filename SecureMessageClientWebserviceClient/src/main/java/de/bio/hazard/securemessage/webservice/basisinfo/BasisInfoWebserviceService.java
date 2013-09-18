
package de.bio.hazard.securemessage.webservice.basisinfo;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BasisInfoWebserviceService", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", wsdlLocation = "http://localhost:8080/basisInfoWebservice?wsdl")
public class BasisInfoWebserviceService
    extends Service
{

    private final static URL BASISINFOWEBSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException BASISINFOWEBSERVICESERVICE_EXCEPTION;
    private final static QName BASISINFOWEBSERVICESERVICE_QNAME = new QName("http://webservice.securemessage.hazard.bio.de/", "BasisInfoWebserviceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/basisInfoWebservice?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BASISINFOWEBSERVICESERVICE_WSDL_LOCATION = url;
        BASISINFOWEBSERVICESERVICE_EXCEPTION = e;
    }

    public BasisInfoWebserviceService() {
        super(__getWsdlLocation(), BASISINFOWEBSERVICESERVICE_QNAME);
    }

    public BasisInfoWebserviceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BASISINFOWEBSERVICESERVICE_QNAME, features);
    }

    public BasisInfoWebserviceService(URL wsdlLocation) {
        super(wsdlLocation, BASISINFOWEBSERVICESERVICE_QNAME);
    }

    public BasisInfoWebserviceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BASISINFOWEBSERVICESERVICE_QNAME, features);
    }

    public BasisInfoWebserviceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BasisInfoWebserviceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BasisInfoWebservice
     */
    @WebEndpoint(name = "BasisInfoWebservicePort")
    public BasisInfoWebservice getBasisInfoWebservicePort() {
        return super.getPort(new QName("http://webservice.securemessage.hazard.bio.de/", "BasisInfoWebservicePort"), BasisInfoWebservice.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BasisInfoWebservice
     */
    @WebEndpoint(name = "BasisInfoWebservicePort")
    public BasisInfoWebservice getBasisInfoWebservicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.securemessage.hazard.bio.de/", "BasisInfoWebservicePort"), BasisInfoWebservice.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BASISINFOWEBSERVICESERVICE_EXCEPTION!= null) {
            throw BASISINFOWEBSERVICESERVICE_EXCEPTION;
        }
        return BASISINFOWEBSERVICESERVICE_WSDL_LOCATION;
    }

}
