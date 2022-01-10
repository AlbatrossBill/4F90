package decorator.read;

import decorator.Publisher;
import org.jdom2.Element;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BaseXMLObjectReader extends Reader {

    private final Element root;

    public BaseXMLObjectReader(Element root) {
        this.root = root;
    }

    @Override
    public <T> void readXMLObject(T t, Element root) {
        if (root == null) {
            root = this.root;
        }
        List<Element> children = root.getChildren();
        Map<String, Element> map = children.stream()
                .collect(Collectors.toMap(Element::getName, Function.identity()));

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (Publisher.isBaseDataType(field)) {
                String name = field.getName();
                Element element = map.get(name);
                if (element == null) {
                    continue;
                }
                String value = element.getValue();
                Publisher.setVal(field, t, value);
            }
        }
    }
}
