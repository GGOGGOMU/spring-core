import framework.ClassLoader;
import framework.LocationScanner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MyMain {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        LocationScanner locationScanner = new LocationScanner();
        try {
            List<String> classes = locationScanner.read();

            for (String loadClassName : classes) {
                System.out.println(loadClassName);
                Class<?> object = ClassLoader.loadClass(loadClassName);
                object.getName();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
