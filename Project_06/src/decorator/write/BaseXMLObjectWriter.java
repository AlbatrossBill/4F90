package decorator.write;

import decorator.Publisher;
import org.jdom2.Element;

import java.lang.reflect.Field;

//primitive, string, object
public class BaseXMLObjectWriter extends Writer {

    Element root;

    public BaseXMLObjectWriter(Element root) {
        this.root = root;
    }

    @Override
    public void writeXMLObject(Object obj) {
        try {
            this.parseOutside(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void parseOutside(Object target) throws IllegalAccessException {

        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!Publisher.isBaseDataType(field)) {
                return;
            }
            String fieldName = field.getName();
            Element child = new Element(fieldName);
            child.setText(String.valueOf(field.get(target)));
            root.addContent(child);
        }
    }
}
