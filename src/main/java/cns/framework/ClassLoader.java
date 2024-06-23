package cns.framework;

import java.lang.reflect.InvocationTargetException;

public class ClassLoader {

    public static Object loadClass(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);

        return clazz.getDeclaredConstructor().newInstance();
    }
}
