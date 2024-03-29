
package org.example.service;

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
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BattleshipServerService", targetNamespace = "http://example.org/", wsdlLocation = "http://localhost:8080/battleship?wsdl")
public class BattleshipServerService
    extends Service
{

    private final static URL BATTLESHIPSERVERSERVICE_WSDL_LOCATION;
    private final static WebServiceException BATTLESHIPSERVERSERVICE_EXCEPTION;
    private final static QName BATTLESHIPSERVERSERVICE_QNAME = new QName("http://example.org/", "BattleshipServerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/battleship?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BATTLESHIPSERVERSERVICE_WSDL_LOCATION = url;
        BATTLESHIPSERVERSERVICE_EXCEPTION = e;
    }

    public BattleshipServerService() {
        super(__getWsdlLocation(), BATTLESHIPSERVERSERVICE_QNAME);
    }

    public BattleshipServerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BATTLESHIPSERVERSERVICE_QNAME, features);
    }

    public BattleshipServerService(URL wsdlLocation) {
        super(wsdlLocation, BATTLESHIPSERVERSERVICE_QNAME);
    }

    public BattleshipServerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BATTLESHIPSERVERSERVICE_QNAME, features);
    }

    public BattleshipServerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BattleshipServerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BattleshipServer
     */
    @WebEndpoint(name = "BattleshipServerPort")
    public BattleshipServer getBattleshipServerPort() {
        return super.getPort(new QName("http://example.org/", "BattleshipServerPort"), BattleshipServer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BattleshipServer
     */
    @WebEndpoint(name = "BattleshipServerPort")
    public BattleshipServer getBattleshipServerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://example.org/", "BattleshipServerPort"), BattleshipServer.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BATTLESHIPSERVERSERVICE_EXCEPTION!= null) {
            throw BATTLESHIPSERVERSERVICE_EXCEPTION;
        }
        return BATTLESHIPSERVERSERVICE_WSDL_LOCATION;
    }

}
