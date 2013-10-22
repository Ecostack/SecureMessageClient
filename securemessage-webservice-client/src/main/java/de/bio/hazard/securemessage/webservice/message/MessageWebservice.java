
package de.bio.hazard.securemessage.webservice.message;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MessageWebservice", targetNamespace = "http://webservice.securemessage.hazard.bio.de/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MessageWebservice {


    /**
     * 
     * @param arg0
     * @return
     *     returns de.bio.hazard.securemessage.webservice.message.RequestMessageWebserviceReturnDTO
     * @throws EncryptionExceptionBiohazard_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMessages", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.message.GetMessages")
    @ResponseWrapper(localName = "getMessagesResponse", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.message.GetMessagesResponse")
    @Action(input = "http://webservice.securemessage.hazard.bio.de/MessageWebservice/getMessagesRequest", output = "http://webservice.securemessage.hazard.bio.de/MessageWebservice/getMessagesResponse", fault = {
        @FaultAction(className = EncryptionExceptionBiohazard_Exception.class, value = "http://webservice.securemessage.hazard.bio.de/MessageWebservice/getMessages/Fault/EncryptionExceptionBiohazard")
    })
    public RequestMessageWebserviceReturnDTO getMessages(
        @WebParam(name = "arg0", targetNamespace = "")
        RequestMessageWebserviceDTO arg0)
        throws EncryptionExceptionBiohazard_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws EncryptionExceptionBiohazard_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "addMessage", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.message.AddMessage")
    @ResponseWrapper(localName = "addMessageResponse", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.message.AddMessageResponse")
    @Action(input = "http://webservice.securemessage.hazard.bio.de/MessageWebservice/addMessageRequest", output = "http://webservice.securemessage.hazard.bio.de/MessageWebservice/addMessageResponse", fault = {
        @FaultAction(className = EncryptionExceptionBiohazard_Exception.class, value = "http://webservice.securemessage.hazard.bio.de/MessageWebservice/addMessage/Fault/EncryptionExceptionBiohazard")
    })
    public void addMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        MessageWebserviceDTO arg0)
        throws EncryptionExceptionBiohazard_Exception
    ;

}