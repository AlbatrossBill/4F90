import java.lang.reflect.Field;
import java.util.*;

public class Test {

    public Test() throws IllegalAccessException {
        // Class Foo
        Class cFoo = null;
        try {
            cFoo = Class.forName("Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object_Foo = null;
        try {
            object_Foo = cFoo.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // Class ObjVar
        Class cObjVar = null;
        try {
            cObjVar = Class.forName("ObjVar");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object_ObjVar = null;
        try {
            object_ObjVar = cObjVar.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // Class MyVar
        Class cMyVar = null;
        try {
            cMyVar = Class.forName("MyVar");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object_MyVar = null;
        try {
            object_MyVar = cMyVar.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // Class MyObj
        Class cMyObj = null;
        try {
            cMyObj = Class.forName("MyObj");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object_MyObj = null;
        try {
            object_MyObj = cMyObj.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        try {
            //set values into object foo
            setFieldValue(cFoo, object_Foo, "age", "23");
            setFieldValue(cFoo, object_Foo, "grade", "73.9");
            setFieldValue(cFoo, object_Foo, "name", "Bill");
            setFieldValue(cObjVar, object_ObjVar, "height", "182");
            setFieldValue(cObjVar, object_ObjVar, "gender", "Male");
            setFieldValue(cObjVar, object_ObjVar, "shoe", "10.5");
            setFieldValue(cMyVar, object_MyVar, "weight", "180");
            setFieldValue(cMyVar, object_MyVar, "gpa", "3.3");
            setFieldValue(cMyVar, object_MyVar, "address", "SilverMaple");
            setFieldValue(cMyObj, object_MyObj, "anInt", "4321");
            setFieldValue(cMyObj, object_MyObj, "aDouble", "99.9");
            setFieldValue(cMyObj, object_MyObj, "aString", "ABC");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\">" + "\n");

        Map<Object, Integer> enc = new HashMap<>();
        enc.put(object_Foo, 0);

        Iterator iterator = enc.entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry mEntry = (Map.Entry) iterator.next();
            Object key = mEntry.getKey();
            int ref = enc.get(key);
            Class cKey = key.getClass();
            sb.append("<object " + "className=" + cKey.getTypeName() + " ref=" + ref + ">" + "\n");

            Field[] fields = cKey.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);

                if (!f.getType().isPrimitive() && !f.getType().getSimpleName().equals("String")) {
                    sb.append("<" + f.getType().getSimpleName() + " varName=" + f.getName() + " ref=" + (ref += 1) + ">" + "\n");
                    enc.clear();

                    if (f.getName().equals("objVar")) {
                        enc.put(object_ObjVar, ref);
                        iterator = enc.entrySet().iterator();
                    }
                    if (f.getName().equals("myVar")) {
                        enc.put(object_MyVar, ref);
                        iterator = enc.entrySet().iterator();
                    }
                    if (f.getName().equals("myObj")) {
                        enc.put(object_MyObj, ref);
                        iterator = enc.entrySet().iterator();
                    }
                } else {
                    sb.append("<" + f.getType().getSimpleName() + " name=" + f.getName() + " value=" + f.get(key) + ">" + "\n");
                }
            }
            sb.append("</object>" + "\n\n");
        }
        System.out.println(sb);
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

    private static void setFieldValue(Class c, Object object, String fieldName, String value) throws IllegalAccessException {
        Field[] fields = getAllFields(c);
        for (Field f :
                fields) {
            f.setAccessible(true);
            if (f.getName().equals(fieldName)) {
                if (f.getType().getTypeName().equals("int")) {
                    int intValue = Integer.parseInt(value);
                    f.set(object, intValue);
                } else if (f.getType().getTypeName().equals("byte")) {
                    byte byteValue = Byte.parseByte(value);
                    f.set(object, byteValue);
                } else if (f.getType().getTypeName().equals("short")) {
                    short shortValue = Short.parseShort(value);
                    f.set(object, shortValue);
                } else if (f.getType().getTypeName().equals("long")) {
                    long longValue = Long.parseLong(value);
                    f.set(object, longValue);
                } else if (f.getType().getTypeName().equals("double")) {
                    double doubleValue = Double.parseDouble(value);
                    f.set(object, doubleValue);
                } else if (f.getType().getTypeName().equals("float")) {
                    float floatValue = Float.parseFloat(value);
                    f.set(object, floatValue);
                } else if (f.getType().getTypeName().equals("boolean")) {
                    boolean boolValue = Boolean.parseBoolean(value);
                    f.set(object, boolValue);
                } else if (f.getType().getTypeName().equals("char")) {
                    char[] chars = value.toCharArray();
                    char charValue = chars[0];
                    f.set(object, charValue);
                } else {
                    f.set(object, value);
                }
            } else {
                continue;
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        Test t = new Test();
    }
}
