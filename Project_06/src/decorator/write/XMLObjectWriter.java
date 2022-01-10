package decorator.write;

import decorator.Publisher;
import org.jdom2.Element;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

//dealing non String
public class XMLObjectWriter extends WriterDecorator {

    private Element root;

    public XMLObjectWriter(Writer writer, Element root) {
        this(writer);
        this.root = root;
    }

    public XMLObjectWriter(Writer writer) {
        super(writer);
    }

    @Override
    public void writeXMLObject(Object obj) {
        if (super.writer != null) {
            super.writer.writeXMLObject(obj);
        }
        try {
            this.writeInner(obj, root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeInner(Object obj, Element root) throws Exception {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Publisher.isBaseDataType(field)) {
                continue;
            }
            field.setAccessible(true);
            Element child = new Element(field.getName());
            root.addContent(child);
            Object c = field.get(obj);

            if (Publisher.hasList(field)) {
                writeList((List<?>) c, child);
                continue;
            }

            Class<?> fieldType = field.getType();
            Field[] fieldFields = fieldType.getDeclaredFields();
            for (Field ff : fieldFields) {
                ff.setAccessible(true);
                boolean b = !Publisher.isBaseDataType(ff);
                Element grandson = new Element(ff.getName());
                if (!b) {
                    grandson.setText(String.valueOf(ff.get(c)));
                }
                child.addContent(grandson);
                Object o = ff.get(c);
                if (b) {
                    this.writeInner(o, grandson);
                }
            }
        }
    }


    private void writeList(List<?> objData, Element element) throws Exception {
        if (objData != null) {
            for (int i = 0; i < objData.size(); i++) {

                Field[] declaredFields = objData.get(i).getClass().getDeclaredFields();
                Object obj = objData.get(i);
                Element root = new Element(obj.getClass().getSimpleName());

                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    Element child = new Element(declaredField.getName());

                    if (declaredField.getGenericType() instanceof ParameterizedType) {
                        List<?> objList = (List<?>) declaredField.get(obj);
                        if (objList != null) {
                            writeList(objList, child);
                            root.addContent(child);
                        }
                    } else {
                        child.setText(String.valueOf(declaredField.get(obj)));
                        root.addContent(child);
                    }
                }
                element.addContent(root);
            }
        }
    }
}
