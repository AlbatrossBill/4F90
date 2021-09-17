import java.lang.reflect.Field;
import java.util.*;

public class Test {

    public Test() {

        Class cFoo = null;
        try {
            cFoo = Class.forName("Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object = null;
        try {
            object = cFoo.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        Map<Object, Integer> enc = new HashMap<>();
        enc.put(object, 0);

        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = null;
        for (Object key : enc.keySet()) {
            Integer ref = enc.get(key);
            Class cKey = key.getClass();

            Field[] fields = cKey.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (sb.length() == 0) {
                    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\">" + "\n");
                    sb.append("<object " + "className=" + cKey.getSimpleName() + " ref=" + ref + ">" + "\n");
                }
                if (field.getType().getSimpleName().equals("objVar")) {
                    ref += 1;
                    sb.append("<" + field.getType().getSimpleName() + " " + "className=" + field.getName() + " ref=" + ref + ">" + "\n");
                    sb1 = new StringBuilder();
                    Field[] fields1 = field.getType().getDeclaredFields();
                    for (Field field1 : fields1) {
                        field1.setAccessible(true);
                        if (sb1.length() == 0) {
                            sb1.append("<object " + "className=" + field.getName() + " ref=" + ref + ">" + "\n");
                        }
                        sb1.append("<" + field1.getType().getSimpleName() + " name=" + field1.getName() + ">" + "\n");
                    }
                } else {
                    sb.append("<" + field.getType().getSimpleName() + " name=" + field.getName() + ">" + "\n");
                }
            }

            sb.append("</object>");
            sb1.append("</object>");
            System.out.println(sb + "\n");
            System.out.println(sb1);
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
    }
}
