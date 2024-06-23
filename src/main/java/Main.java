import yunha.framework.BeanFactory;
import yunha.framework.LocationScanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        BeanFactory factory = new BeanFactory();
        LocationScanner scanner = new LocationScanner(factory);
        String packagePath = "test.user";
        scanner.scan(packagePath);
        System.out.println("done");
    }
}
