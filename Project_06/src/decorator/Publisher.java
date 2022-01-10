package decorator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public interface Publisher {
    static boolean isBaseDataType(Field f) {
        Class<?>[] cl = {
                byte.class, Byte.class,
                short.class, Short.class,
                int.class, Integer.class,
                long.class, Long.class,
                float.class, Float.class,
                double.class, Double.class,
                boolean.class, Boolean.class,
                char.class, Character.class,
                String.class, Object.class
        };
        return Arrays.asList(cl).contains(f.getType());
    }

    static void setVal(Field field, Object t, String value) {
        try {
            Class<?> tp = field.getType();
            Object val = null;
            if (tp == Integer.class || tp == int.class) {
                val = Integer.parseInt(value);
            } else if (tp == byte.class || tp == Byte.class) {
                val = Byte.parseByte(value);
            } else if (tp == short.class || tp == Short.class) {
                val = Short.parseShort(value);
            } else if (tp == long.class || tp == Long.class) {
                val = Long.parseLong(value);
            } else if (tp == float.class || tp == Float.class) {
                val = Float.parseFloat(value);
            } else if (tp == double.class || tp == Double.class) {
                val = Double.parseDouble(value);
            } else if (tp == boolean.class || tp == Boolean.class) {
                val = Boolean.parseBoolean(value);
            } else if (tp == char.class || tp == Character.class) {
                val = value.toCharArray()[0];
            } else if (tp == String.class) {
                val = value;
            }
            field.set(t, val);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static boolean hasList(Field f) {
        return f.getGenericType() instanceof ParameterizedType;
    }
}
