import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class buildXML {

    public buildXML() {
    }

    public void createXmlFile() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();//create an object of dbf

        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = db.newDocument();
        document.setXmlStandalone(false);//<?xml version="1.0" encoding="UTF-8" standalone="no"?>

        //create content in xml
        document.appendChild(createElement(document));

        TransformerFactory tff = TransformerFactory.newInstance();

        Transformer tf = null;
        try {
            tf = tff.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        tf.setOutputProperty(OutputKeys.INDENT, "yes"); //set change line in output file

        try {
            tf.transform(new DOMSource(document), new StreamResult(new File("test.xml")));
            System.out.println("creating success");
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private Node createElement(Document document) {
        //create node and set attribute
        Element object = document.createElement("object");
        object.setAttribute("className", "Foo");

        //create child nod and set attribute
        Element field_Int = document.createElement("field");
        Element field_Byte = document.createElement("field");
        Element field_Short = document.createElement("field");
        Element field_Long = document.createElement("field");
        Element field_Float = document.createElement("field");
        Element field_Double = document.createElement("field");
        Element field_Boolean = document.createElement("field");
        Element field_Char = document.createElement("field");
        Element field_String = document.createElement("field");

        field_Int.setAttribute("type", "int");
        field_Int.setAttribute("value", "42");
        field_Byte.setAttribute("type", "byte");
        field_Byte.setAttribute("value", "8");
        field_Short.setAttribute("type", "short");
        field_Short.setAttribute("value", "9");
        field_Long.setAttribute("type", "Long");
        field_Long.setAttribute("value", "50");
        field_Float.setAttribute("type", "float");
        field_Float.setAttribute("value", "8.45");
        field_Double.setAttribute("type", "double");
        field_Double.setAttribute("value", "12.65");
        field_Boolean.setAttribute("type", "boolean");
        field_Boolean.setAttribute("value", "true");
        field_Char.setAttribute("type", "char");
        field_Char.setAttribute("value", "a");
        field_String.setAttribute("type", "string");
        field_String.setAttribute("value", "HelloWorld");

        object.appendChild(field_Int);
        object.appendChild(field_Byte);
        object.appendChild(field_Short);
        object.appendChild(field_Long);
        object.appendChild(field_Float);
        object.appendChild(field_Double);
        object.appendChild(field_Boolean);
        object.appendChild(field_Char);
        object.appendChild(field_String);
        return object;
    }

    public static void main(String[] args) {
        buildXML b = new buildXML();
        b.createXmlFile();
    }
}
