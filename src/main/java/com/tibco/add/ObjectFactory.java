//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.18 at 11:44:03 PM PKT 
//


package com.tibco.add;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tibco.add package. 
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

    private final static QName _Username_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "Username");
    private final static QName _Password_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "Password");
    private final static QName _MSISDN_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "MSISDN");
    private final static QName _TransactionID_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "TransactionID");
    private final static QName _Name_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "Name");
    private final static QName _Value_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "Value");
    private final static QName _Type_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "Type");
    private final static QName _Status_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "Status");
    private final static QName _Message_QNAME = new QName("http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", "Message");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tibco.add
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddProductIn }
     * 
     */
    public AddProductIn createAddProductIn() {
        return new AddProductIn();
    }

    /**
     * Create an instance of {@link ListOfAttributes }
     * 
     */
    public ListOfAttributes createListOfAttributes() {
        return new ListOfAttributes();
    }

    /**
     * Create an instance of {@link Attribute }
     * 
     */
    public Attribute createAttribute() {
        return new Attribute();
    }

    /**
     * Create an instance of {@link AddProductOut }
     * 
     */
    public AddProductOut createAddProductOut() {
        return new AddProductOut();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "Username")
    public JAXBElement<String> createUsername(String value) {
        return new JAXBElement<String>(_Username_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "Password")
    public JAXBElement<String> createPassword(String value) {
        return new JAXBElement<String>(_Password_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "MSISDN")
    public JAXBElement<String> createMSISDN(String value) {
        return new JAXBElement<String>(_MSISDN_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "TransactionID")
    public JAXBElement<String> createTransactionID(String value) {
        return new JAXBElement<String>(_TransactionID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "Name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "Value")
    public JAXBElement<String> createValue(String value) {
        return new JAXBElement<String>(_Value_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "Type")
    public JAXBElement<String> createType(String value) {
        return new JAXBElement<String>(_Type_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "Status")
    public JAXBElement<String> createStatus(String value) {
        return new JAXBElement<String>(_Status_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd", name = "Message")
    public JAXBElement<String> createMessage(String value) {
        return new JAXBElement<String>(_Message_QNAME, String.class, null, value);
    }

}