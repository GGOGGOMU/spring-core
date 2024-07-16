package cns;

import cns.framework.*;
import cns.framework.ClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CnsMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // 패키지 스캔
        AnnotationScanner scanner = new AnnotationScanner();
//        LocationScanner scanner = new LocationScanner();
//        PublicMethodScanner scanner = new PublicMethodScanner();
//        List<String> classNames = scanner.read("cns.users");
        List<String> classNames = scanner.read("cns.annotation_user");

        // 빈 팩토리 생성 및 빈 등록
        BeanFactory beanFactory = new BeanFactory();
        for (String className : classNames) {
            Object o = ClassLoader.loadClass(className);
            beanFactory.registerSingleton(className, o);

            List<Object> o2 = PublicClassLoader.loadClass(className);
            for (Object o1 : o2) {
                beanFactory.registerSingleton(o1.getClass().getName(), o1);
            }
        }

        // 등록된 빈 확인
        beanFactory.getRegisteredBean();


    }
}
