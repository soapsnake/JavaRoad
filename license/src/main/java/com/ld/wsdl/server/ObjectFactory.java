
package com.ld.wsdl.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ld.wsdl.server package. 
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

    private final static QName _TestWebService_QNAME = new QName("http://server.wsdl.ld.com/", "testWebService");
    private final static QName _TestWebServiceResponse_QNAME = new QName("http://server.wsdl.ld.com/", "testWebServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ld.wsdl.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestWebService }
     * 
     */
    public TestWebService createTestWebService() {
        return new TestWebService();
    }

    /**
     * Create an instance of {@link TestWebServiceResponse }
     * 
     */
    public TestWebServiceResponse createTestWebServiceResponse() {
        return new TestWebServiceResponse();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestWebService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.wsdl.ld.com/", name = "testWebService")
    public JAXBElement<TestWebService> createTestWebService(TestWebService value) {
        return new JAXBElement<TestWebService>(_TestWebService_QNAME, TestWebService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestWebServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.wsdl.ld.com/", name = "testWebServiceResponse")
    public JAXBElement<TestWebServiceResponse> createTestWebServiceResponse(TestWebServiceResponse value) {
        return new JAXBElement<TestWebServiceResponse>(_TestWebServiceResponse_QNAME, TestWebServiceResponse.class, null, value);
    }

}
