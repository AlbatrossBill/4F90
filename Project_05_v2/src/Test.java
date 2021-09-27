import java.lang.reflect.Field;
import java.util.*;

public class Test {

    public Test() {
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
        Map<Object, Integer> enc = new HashMap<>();
        enc.put(object_Foo, 0);
        enc.put(object_ObjVar, 1);
        enc.put(object_MyVar, 2);
        enc.put(object_MyObj,3);

        StringBuilder sb = new StringBuilder();
        for (Object key : enc.keySet()) {
            Integer ref = enc.get(key);

            Class cKey = key.getClass();
            if (sb.length() == 0) {
                sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\">" + "\n");
            }
            sb.append("<object " + "className=" + cKey.getTypeName() + " ref=" + ref + ">" + "\n");
            Field[] fields = cKey.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().getSimpleName().equals("ObjVar")) {
                    sb.append("<" + field.getType().getSimpleName() + " " + "className=" + field.getType().getSimpleName() + " ref=" + (ref + 1) + ">" + "\n");
                } else {
                    if (field.getType().getSimpleName().equals("MyVar")) {
                        sb.append("<" + field.getType().getSimpleName() + " " + "className=" + field.getType().getSimpleName() + " ref=" + (ref + 1) + ">" + "\n");
                    } else {
                        if (field.getType().getSimpleName().equals("MyObj")) {
                            sb.append("<" + field.getType().getSimpleName() + " " + "className=" + field.getType().getSimpleName() + " ref=" + (ref + 1) + ">" + "\n");
                        } else {
                            sb.append("<" + field.getType().getSimpleName() + " name=" + field.getName() + ">" + "\n");
                        }
                    }
                }

            }
            sb.append("</object>" + "\n\n");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) {
        Test test = new Test();
    }
}
