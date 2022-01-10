package decorator.write;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintXml extends Writer {

    private final Element e;
    private final Writer writer;

    public PrintXml(Element e, Writer writer) {
        this.e = e;
        this.writer = writer;
    }

    @Override
    public void writeXMLObject(Object obj) {
        writer.writeXMLObject(obj);
        try {
            Document doc = new Document(e);
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            File directory = new File("src/xml/");
            String courseFile = directory.getCanonicalPath();
            // output
            File f = new File(courseFile);
            if (!f.exists()) {
                f.mkdirs();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHssmm");
            File file = new File(f.getAbsolutePath() +
                    File.separator + obj.getClass().getSimpleName() + format.format(new Date()) + ".xml");
            xmlOutput.output(doc, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成xml文件出现异常：" + e.getMessage());
        }
    }
}
