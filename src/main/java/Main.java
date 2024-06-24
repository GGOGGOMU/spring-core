import framework.BeanFactory;
import framework.LocationScanner;
import user.Dog;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BeanFactory beanFactory = new BeanFactory();
        LocationScanner locationScanner = getClassPathBeanDefinitionScanner(beanFactory);


        locationScanner.scan("user");
        beanFactory.registerSingleton("dog1", new Dog("dog1", 1));


        Dog dog = new Dog();
        dog.setAge(2);
        dog.setName("dog2");

        beanFactory.registerSingleton("dog2", dog);

        System.out.println("등록 된 빈의 갯수 : " + beanFactory.getBeanDefinitionCount());
        System.out.println("빈의 목록 : " + beanFactory.getBeans());


    }

    protected static LocationScanner getClassPathBeanDefinitionScanner(BeanFactory beanFactory) {
        return new LocationScanner(beanFactory);
    }
}
