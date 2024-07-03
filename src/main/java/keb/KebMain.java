package keb;


import cns.users.Dog;
import keb.framework.BeanFactory;
import keb.framework.LocationScanner;
import keb.framework.user.UserConstructors;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class KebMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory = new BeanFactory();
        LocationScanner locationScanner = getClassPathBeanDefinitionScanner(beanFactory);


        locationScanner.scan("user");
        beanFactory.registerSingleton("dog1", new Dog("dog1", 1));


        Dog dog = new Dog();
        dog.setAge(2);
        dog.setName("dog2");

        beanFactory.registerSingleton("dog2", dog);


        // Dog 클래스의 인스턴스 생성

        List<Method> publicMethods = LocationScanner.findPublicMethods(UserConstructors.class);

        for (Method method : publicMethods) {
            System.out.println("method : " + method.getName());
            try {
                String beanName = method.getName();
                Object beanObject;

                if (method.getReturnType() == Void.TYPE) {
                    continue;
                }

//                if (Modifier.isStatic(method.getModifiers())) {
//                    beanObject = method.invoke(null);
//                } else {
                    //TODO : constructorResolvor, autowireConstructor
                    beanObject = method.invoke(method.getDeclaringClass().getConstructor().newInstance());
//                }

                if (beanObject != null) {
                    beanFactory.registerSingleton(beanName, beanObject);
                } else {
                    System.out.println("Failed to register bean: " + beanName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("등록 된 빈의 갯수 : " + beanFactory.getBeanDefinitionCount());
        System.out.println("빈의 목록 : " + beanFactory.getBeans());


    }

    protected static LocationScanner getClassPathBeanDefinitionScanner(BeanFactory beanFactory) {
        return new LocationScanner(beanFactory);
    }
}
