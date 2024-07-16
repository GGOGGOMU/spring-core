package yunha;

import yunha.framework.BeanFactory;
import yunha.framework.LocationScanner;

import java.lang.reflect.InvocationTargetException;

public class YunhaMain {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        BeanFactory factory = new BeanFactory();
        LocationScanner scanner = new LocationScanner(factory);

        // Class Bean
        String modelPackagePath = "yunha.model";
        scanner.classScanner(modelPackagePath);

        // Method Bean
        String configPackagePath = "yunha.scan";
        scanner.methodScanner(configPackagePath);

        // Annotation Bean
        String rootPackagePath = "yunha";
        scanner.annotationScanner(rootPackagePath);

        factory.getRegisteredBean();
    }
}
