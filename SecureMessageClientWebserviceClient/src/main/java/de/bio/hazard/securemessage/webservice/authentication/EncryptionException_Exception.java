
package de.bio.hazard.securemessage.webservice.authentication;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EncryptionException", targetNamespace = "http://webservice.securemessage.hazard.bio.de/")
public class EncryptionException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EncryptionException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public EncryptionException_Exception(String message, EncryptionException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public EncryptionException_Exception(String message, EncryptionException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: de.bio.hazard.securemessage.webservice.authentication.EncryptionException
     */
    public EncryptionException getFaultInfo() {
        return faultInfo;
    }

}
