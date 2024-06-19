package test.framework;


import org.reflections.scanners.SubTypesScanner;
import test.bean.TestBean;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.File;
import java.util.*;

/**
 * description
 * test.user 하위 디렉터리에 있는 클래스를 읽어 들이는 클래스
 * */
public class LocationScanner {

//    public int scan(String... basePackages) {
//        int beanCountAtScanStart = this.registry.getBeanDefinitionCount();
//
//        doScan(basePackages);
//
//        // Register annotation config processors, if necessary.
//        if (this.includeAnnotationConfig) {
//            AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
//        }
//
//        return (this.registry.getBeanDefinitionCount() - beanCountAtScanStart);
//    }
    private final BeanFactory beanFactory;

    public LocationScanner(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Set<Class> scan(String... basePackages) {
        if(basePackages == null || basePackages.length == 0)
            return new HashSet<Class>();

        Set<Class> classes = new HashSet<Class>();

        for(String basePackageName : basePackages) {
            Reflections reflections = new Reflections(basePackageName, new SubTypesScanner(false));
            classes.addAll(reflections.getSubTypesOf(Object.class));
        }

        classes.forEach(clazz -> {
            beanFactory.registerSingleton(clazz.getSimpleName(), new TestBean(clazz.getSimpleName()));
            System.out.println(clazz.getSimpleName());
        });

        /*for(String basePackageName : basePackages) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = basePackageName.replace('.', '/');


            try {
                List<File> files = new ArrayList<File>();
                Enumeration<URL> resources = classLoader.getResources(path);
                while (resources.hasMoreElements()) {
                    URL resource = resources.nextElement();
                    files.add(new File(resource.getFile()));
                }
                for (File file : files) {
                    if (file.isDirectory()) {
                        //System.out.println("[Directory] " + file.getAbsolutePath());
                        classes.addAll(findClasses(file, basePackageName));
                        beanFactory.registerSingleton(file.getName(), new TestBean(file.getName()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        return classes;
    }


}
