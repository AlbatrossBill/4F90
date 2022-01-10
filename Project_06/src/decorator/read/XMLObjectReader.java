package decorator.read;

import decorator.Publisher;
import org.jdom2.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XMLObjectReader extends ReaderDecorator {

    public XMLObjectReader(Reader reader) {
        super(reader);
    }

    @Override
    public <T> void readXMLObject(T t, Element root) {
        if (super.reader != null) {
            super.reader.readXMLObject(t, root);
        }
        this.readInner(t, root);
    }

    private void readInner(Object t, Element root) {
        //parse
        Field[] fields = t.getClass().getDeclaredFields();
        List<Element> children = root.getChildren();

        Map<String, Element> map = children.stream()
                .collect(Collectors.toMap(Element::getName, Function.identity()));
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Element element = map.get(field.getName());

                //if is primitive type, using super parse
                if (Publisher.isBaseDataType(field)) {
                    super.readXMLObject(t, root);
                } else {
                    //list
                    boolean list = Publisher.hasList(field);
                    if (list) {
                        parseList(t, field, element);
                        continue;
                    }
                    Object o = field.getType().newInstance();
                    field.set(t, o);
                    this.readInner(o, element);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void parseList(Object t, Field f, Element ele)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        List<Object> list = new ArrayList<>();

        ParameterizedType pt = (ParameterizedType) f.getGenericType();
        Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];

        List<Element> elements = ele.getChildren();
        for (Element element : elements) {
            Object eleInstance = genericClazz.getConstructor().newInstance();

            Map<String, Element> map = element.getChildren().stream().collect(Collectors.toMap(Element::getName, Function.identity()));

            Field[] fields = genericClazz.getDeclaredFields();
            for (Field instanceField : fields) {
                instanceField.setAccessible(true);
                Publisher.setVal(instanceField, eleInstance, map.get(instanceField.getName()).getValue());
            }
            list.add(eleInstance);
        }
        f.set(t, list);
    }
}
