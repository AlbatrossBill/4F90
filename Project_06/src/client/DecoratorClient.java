package client;

import decorator.read.BaseXMLObjectReader;
import decorator.read.XMLObjectReader;
import decorator.write.BaseXMLObjectWriter;
import decorator.write.PrintXml;
import decorator.write.XMLObjectWriter;
import none.Foo;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

//client class，two main method，write xml and read xml
public class DecoratorClient {

    //create xml file please run writeObj()
    //read please run readXml(finalPath + "xml File name")
    public static void main(String[] args) {
        String finalPath = "src\\xml\\";
        //writeObj();
        readXml(finalPath + "Foo20211222203744.xml");
    }

    //Making Object into xml， xml file name will change base on run time
    public static void writeObj() {
        Foo foo = Foo.getInstance();
        Element root = new Element(foo.getClass().getSimpleName());
        BaseXMLObjectWriter base = new BaseXMLObjectWriter(root);
        XMLObjectWriter writer = new XMLObjectWriter(base, root);
        new PrintXml(root, writer).writeXMLObject(foo);
    }

    public static void readXml(String path) {
        try {
            Foo f = new Foo();
            Element root = new SAXBuilder().build(path).getRootElement();
            BaseXMLObjectReader reader = new BaseXMLObjectReader(root);
            XMLObjectReader xmlReader = new XMLObjectReader(reader);
            xmlReader.readXMLObject(f, root);
            System.out.println(f);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
