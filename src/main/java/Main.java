import framework.BeanFactory;
import framework.LocationScanner;
import user.Dog;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BeanFactory beanFactory = new BeanFactory();
        LocationScanner locationScanner = getClassPathBeanDefinitionScanner(beanFactory);


        locationScanner.scan("user");
        beanFactory.registerSingleton("dog", new Dog("dog", 1));

        System.out.println("Hello world!");
    }

    protected static LocationScanner getClassPathBeanDefinitionScanner(BeanFactory beanFactory) {
        return new LocationScanner(beanFactory);
    }
}
