import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;

public class buildXml {

    public buildXml() {
    }

    public static void createXmlFile() throws IOException {
        StringBuffer sb = new StringBuffer();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();//create an object of dbf
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document=db.newDocument();
        document.setXmlStandalone(false);
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\">" + "\n");
        sb.append("<object className=\"Foo\">" + "\n");
        sb.append("<field type=”int” value=”42”/>" + "\n");
        sb.append("<field type=”byte” value=”8”/>" + "\n");
        sb.append("<field type=”short” value=”12”/>" + "\n");
        sb.append("<field type=”long” value=”50”/>" + "\n");
        sb.append("<field type=”float” value=”12.3”/>" + "\n");
        sb.append("<field type=”double” value=”15.86”/>" + "\n");
        sb.append("<field type=”boolean” value=”false”/>" + "\n");
        sb.append("<field type=”char” value=”a”/>" + "\n");
        sb.append("<field type=”string” value=”HelloWorld”/>" + "\n");
        sb.append("</object>");

        File file = new File("test");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(sb.toString().getBytes("utf8"));
        fos.flush();
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        buildXml buildXml = new buildXml();
        buildXml.createXmlFile();
    }
}
