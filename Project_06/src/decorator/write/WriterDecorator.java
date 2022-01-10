package decorator.write;

public abstract class WriterDecorator extends Writer {

    protected final Writer writer;

    public WriterDecorator(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void writeXMLObject(Object obj) {
        this.writer.writeXMLObject(obj);
    }
}
