//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.10.03 at 02:07:26 PM PKT 
//


package wsdl.remove.business.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd2}Username" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd2}Password" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd2}MSISDN" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd2}TransactionID" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.tibco.com/schemas/MEF/SharedResources/Schemas/SecureAdapters/NotifyMe/Schema.xsd2}ListOfAttributes" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "username",
    "password",
    "msisdn",
    "transactionID",
    "listOfAttributes"
})
@XmlRootElement(name = "RemoveProductIn")
public class RemoveProductIn {

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "MSISDN")
    protected String msisdn;
    @XmlElement(name = "TransactionID")
    protected String transactionID;
    @XmlElement(name = "ListOfAttributes")
    protected ListOfAttributes listOfAttributes;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSISDN() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSISDN(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the listOfAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link ListOfAttributes }
     *     
     */
    public ListOfAttributes getListOfAttributes() {
        return listOfAttributes;
    }

    /**
     * Sets the value of the listOfAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfAttributes }
     *     
     */
    public void setListOfAttributes(ListOfAttributes value) {
        this.listOfAttributes = value;
    }

}
