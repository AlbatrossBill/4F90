package none;

import java.util.List;

public enum TypeKinds {
    
    PRIMITIVE, STRING, ARRAY, LIST, OBJECT, ARRAYLIST, LINKEDLIST, VECTOR;
    
    public static TypeKinds classify(Class<?> clazz) {
        TypeKinds result;
        if (   clazz.isPrimitive() 
            || clazz == Integer.class 
            || clazz == Long.class
            || clazz == Boolean.class
            || clazz == Byte.class
            || clazz == Character.class
            || clazz == Float.class 
            || clazz == Double.class
            || clazz == Short.class
            || clazz == Void.class) {    
            result = PRIMITIVE;
        } else {
            if (clazz == String.class) {
                result = STRING;
            } else {
                if (clazz.isArray()) {
                    result = ARRAY;
                } else {
                    if (List.class.isAssignableFrom(clazz)) {
                        result = LIST;
                    } else {
                        result = OBJECT;
                    }
                }
            }
        }
        return result;
    }
}
