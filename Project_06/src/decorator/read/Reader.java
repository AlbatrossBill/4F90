package decorator.read;

import org.jdom2.Element;

public abstract class Reader {
    abstract <T> void readXMLObject(T t, Element root);
}
