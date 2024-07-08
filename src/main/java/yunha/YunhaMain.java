package yunha;

import yunha.framework.BeanFactory;
import yunha.framework.LocationScanner;

import java.lang.reflect.InvocationTargetException;

public class YunhaMain {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("Hello world!");
        BeanFactory factory = new BeanFactory();
        LocationScanner scanner = new LocationScanner(factory);
        String modelPackagePath = "yunha.model";
        scanner.classScanner(modelPackagePath);
        String configPackagePath = "yunha.config";
        scanner.methodScanner(configPackagePath);
        System.out.println("done");
    }
}
