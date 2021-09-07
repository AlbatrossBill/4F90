import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        //create class object
        Class c = null;
        try {
            c = Class.forName("Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //create object foo from class object
        Foo foo = null;
        try {
            foo = (Foo) c.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        //set values into object foo
        setFieldValue(c, foo, "age", "23");
        setFieldValue(c, foo, "avg", "73.9");
        setFieldValue(c, foo, "name", "Bill");
        setFieldValue(c, foo, "univ", "Brock");
        setFieldValue(c, foo, "gender", "Male");
        setFieldValue(c, foo, "aShort", "3");
        setFieldValue(c, foo, "aLong", "45");
        setFieldValue(c, foo, "aByte", "2");
        setFieldValue(c, foo, "aFloat", "12.3");
        setFieldValue(c, foo, "aBoolean", "true");
        setFieldValue(c, foo, "aChar", "H");

        //print
        Field[] fields = getAllFields(c);
        for (Field f : fields) {
            f.setAccessible(true);
            System.out.println(f + ":" + f.get(foo));
        }
    }

    public static Field[] getAllFields(Class clazz) {
        List<Field> allFields = new ArrayList<>();

        //add subclass var into list
        allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        //add super class var into list
        allFields.addAll(Arrays.asList((clazz.getSuperclass().getDeclaredFields())));

        Field[] fields = new Field[allFields.size()];
        allFields.toArray(fields);
        return fields;
    }


    private static void setFieldValue(Class c, Foo foo, String fieldName, String value) throws IllegalAccessException {
        Field[] fields = getAllFields(c);
        for (Field f :
                fields) {
            f.setAccessible(true);
            if (f.getName().equals(fieldName)) {
                if (f.getType().getTypeName().equals("int")) {
                    int intValue = Integer.parseInt(value);
                    f.set(foo, intValue);
                } else if (f.getType().getTypeName().equals("byte")) {
                    byte byteValue = Byte.parseByte(value);
                    f.set(foo, byteValue);
                } else if (f.getType().getTypeName().equals("short")) {
                    short shortValue = Short.parseShort(value);
                    f.set(foo, shortValue);
                } else if (f.getType().getTypeName().equals("long")) {
                    long longValue = Long.parseLong(value);
                    f.set(foo, longValue);
                } else if (f.getType().getTypeName().equals("double")) {
                    double doubleValue = Double.parseDouble(value);
                    f.set(foo, doubleValue);
                } else if (f.getType().getTypeName().equals("float")) {
                    float floatValue = Float.parseFloat(value);
                    f.set(foo, floatValue);
                } else if (f.getType().getTypeName().equals("boolean")) {
                    boolean boolValue = Boolean.parseBoolean(value);
                    f.set(foo, boolValue);
                } else if (f.getType().getTypeName().equals("char")) {
                    char[] chars = value.toCharArray();
                    char charValue = chars[0];
                    f.set(foo, charValue);
                } else {
                    f.set(foo, value);
                }
            } else {
                continue;
            }
        }
    }
}