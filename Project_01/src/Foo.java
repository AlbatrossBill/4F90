import java.lang.reflect.Field;

public class Foo {

    private int age;
    private double avg;
    public String name;
    public String univ;
    public String gender;
    private short aShort;
    long aLong;
    public byte aByte;
    protected float aFloat;
    public boolean aBoolean;
    private char aChar;

    public Foo() {
    }


    public static void main(String[] args) throws IllegalAccessException {
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
        Class aClass = foo.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f + ":" + f.get(foo));
            if (f.get(foo).equals("Male")) {
                System.out.println("-----------------");
            }
        }
    }

    private static void setFieldValue(Class c, Foo foo, String fieldName, String value) throws IllegalAccessException {
        try {
            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
            if (field.getType().getTypeName().equals("int")) {
                int intValue = Integer.parseInt(value);
                field.set(foo, intValue);
            } else if (field.getType().getTypeName().equals("byte")) {
                byte byteValue = Byte.parseByte(value);
                field.set(foo, byteValue);
            } else if (field.getType().getTypeName().equals("short")) {
                short shortValue = Short.parseShort(value);
                field.set(foo, shortValue);
            } else if (field.getType().getTypeName().equals("long")) {
                long longValue = Long.parseLong(value);
                field.set(foo, longValue);
            } else if (field.getType().getTypeName().equals("double")) {
                double doubleValue = Double.parseDouble(value);
                field.set(foo, doubleValue);
            } else if (field.getType().getTypeName().equals("float")) {
                float floatValue = Float.parseFloat(value);
                field.set(foo, floatValue);
            } else if (field.getType().getTypeName().equals("boolean")) {
                boolean boolValue = Boolean.parseBoolean(value);
                field.set(foo, boolValue);
            } else if (field.getType().getTypeName().equals("char")) {
                char[] chars = value.toCharArray();
                char charValue = chars[0];
                field.set(foo, charValue);
            } else {
                field.set(foo, value);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
