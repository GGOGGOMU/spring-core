package cns.framework;

import java.lang.reflect.InvocationTargetException;

public class ClassLoader {

    public static Class<?> loadClass(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);

        // 인스턴스 생성
        Object instance = clazz.getDeclaredConstructor().newInstance();

        return (Class<?>) instance;
    }
}
