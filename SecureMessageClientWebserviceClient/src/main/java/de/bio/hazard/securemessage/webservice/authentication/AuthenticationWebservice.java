
package de.bio.hazard.securemessage.webservice.authentication;

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
@WebService(name = "AuthenticationWebservice", targetNamespace = "http://webservice.securemessage.hazard.bio.de/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AuthenticationWebservice {


    /**
     * 
     * @param arg0
     * @return
     *     returns de.bio.hazard.securemessage.webservice.authentication.NewDeviceWebserviceReturnDTO
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addNewDevice", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.authentication.AddNewDevice")
    @ResponseWrapper(localName = "addNewDeviceResponse", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.authentication.AddNewDeviceResponse")
    @Action(input = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/addNewDeviceRequest", output = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/addNewDeviceResponse")
    public NewDeviceWebserviceReturnDTO addNewDevice(
        @WebParam(name = "arg0", targetNamespace = "")
        NewDeviceWebserviceDTO arg0);

    /**
     * 
     * @param arg0
     * @throws EncryptionExceptionBiohazard_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "addNewUser", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.authentication.AddNewUser")
    @ResponseWrapper(localName = "addNewUserResponse", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.authentication.AddNewUserResponse")
    @Action(input = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/addNewUserRequest", output = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/addNewUserResponse", fault = {
        @FaultAction(className = EncryptionExceptionBiohazard_Exception.class, value = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/addNewUser/Fault/EncryptionExceptionBiohazard")
    })
    public void addNewUser(
        @WebParam(name = "arg0", targetNamespace = "")
        NewUserWebserviceDTO arg0)
        throws EncryptionExceptionBiohazard_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepOneReturnDTO
     * @throws EncryptionExceptionBiohazard_Exception
     * @throws DeviceNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "authenticateStepOne", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.authentication.AuthenticateStepOne")
    @ResponseWrapper(localName = "authenticateStepOneResponse", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.authentication.AuthenticateStepOneResponse")
    @Action(input = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/authenticateStepOneRequest", output = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/authenticateStepOneResponse", fault = {
        @FaultAction(className = EncryptionExceptionBiohazard_Exception.class, value = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/authenticateStepOne/Fault/EncryptionExceptionBiohazard"),
        @FaultAction(className = DeviceNotFoundException_Exception.class, value = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/authenticateStepOne/Fault/DeviceNotFoundException")
    })
    public AuthenticationStepOneReturnDTO authenticateStepOne(
        @WebParam(name = "arg0", targetNamespace = "")
        AuthenticationStepOneDTO arg0)
        throws DeviceNotFoundException_Exception, EncryptionExceptionBiohazard_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns de.bio.hazard.securemessage.webservice.authentication.AuthenticationStepTwoReturnDTO
     * @throws EncryptionExceptionBiohazard_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "authenticateStepTwo", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.authentication.AuthenticateStepTwo")
    @ResponseWrapper(localName = "authenticateStepTwoResponse", targetNamespace = "http://webservice.securemessage.hazard.bio.de/", className = "de.bio.hazard.securemessage.webservice.authentication.AuthenticateStepTwoResponse")
    @Action(input = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/authenticateStepTwoRequest", output = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/authenticateStepTwoResponse", fault = {
        @FaultAction(className = EncryptionExceptionBiohazard_Exception.class, value = "http://webservice.securemessage.hazard.bio.de/AuthenticationWebservice/authenticateStepTwo/Fault/EncryptionExceptionBiohazard")
    })
    public AuthenticationStepTwoReturnDTO authenticateStepTwo(
        @WebParam(name = "arg0", targetNamespace = "")
        AuthenticationStepTwoDTO arg0)
        throws EncryptionExceptionBiohazard_Exception
    ;

}
