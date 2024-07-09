package keb.framework;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * description
 * user 하위 디렉터리에 있는 클래스를 읽어 들이는 클래스
 * */
public class LocationScanner {

    private final BeanFactory beanFactory;

    public LocationScanner(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public static List<Class<?>> doScan(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files == null) throw new AssertionError();
        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().contains(".")) throw new AssertionError();
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public List<Class<?>> getAnnotatedClasses(String packageName, Class... annotations) throws ClassNotFoundException, IOException {
        List<Class<?>> allClasses = doScan(packageName);
        List<Class<?>> annotatedClasses = new ArrayList<>();
        for (Class<?> clazz : allClasses) {
            for (Class annotation : annotations) {
                if (clazz.isAnnotationPresent(annotation)) {
                    annotatedClasses.add(clazz);
                    break;
                }
            }
        }
        return annotatedClasses;
    }
}