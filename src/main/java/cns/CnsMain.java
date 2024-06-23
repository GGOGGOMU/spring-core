package cns;

import cns.framework.BeanFactory;
import cns.framework.ClassLoader;
import cns.framework.LocationScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CnsMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // 패키지 스캔
        LocationScanner locationScanner = new LocationScanner();
        List<String> classNames = locationScanner.read("cns.users");

        // 빈 팩토리 생성 및 빈 등록
        BeanFactory beanFactory = new BeanFactory();
        for (String className : classNames) {
            Object o = ClassLoader.loadClass(className);
            beanFactory.registerSingleton(className, o);
        }

        // 등록된 빈 확인
        beanFactory.getRegisteredBean();


    }
}
