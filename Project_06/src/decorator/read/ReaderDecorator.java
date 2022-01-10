package decorator.read;

import org.jdom2.Element;

public abstract class ReaderDecorator extends Reader {
    protected final Reader reader;

    public ReaderDecorator(Reader reader) {
        this.reader = reader;
    }

    @Override
    <T> void readXMLObject(T t, Element root) {
        this.reader.readXMLObject(t, root);
    }
}
