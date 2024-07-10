package keb.framework;

import keb.framework.annotations.Bean;
import keb.framework.annotations.Component;
import keb.framework.annotations.Configuration;

import java.lang.reflect.Method;
import java.util.List;

import static keb.framework.BeanUtils.convertBeanName;

public class AnnotationProcessor {

    private final BeanFactory beanFactory;
    private final LocationScanner locationScanner;


    public AnnotationProcessor(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.locationScanner = new LocationScanner(beanFactory);
    }

    public void processAnnotations(String basePackage) throws Exception {
        List<Class<?>> componentClasses = locationScanner.getAnnotatedClasses(basePackage, Component.class);
        List<Class<?>> configurationClasses = locationScanner.getAnnotatedClasses(basePackage, Configuration.class);

        for (Class<?> clazz : componentClasses) {
            annotatedComponent(clazz);
        }

        for (Class<?> clazz : configurationClasses) {
            annotatedConfiguration(clazz);
        }
    }

    private void annotatedComponent(Class<?> clazz) throws Exception {
        if (clazz.isAnnotationPresent(keb.framework.annotations.Component.class)) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            beanFactory.registerSingleton(convertBeanName(clazz.getSimpleName().trim()), instance);
        }
    }

    private void annotatedConfiguration(Class<?> clazz) throws Exception {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(keb.framework.annotations.Bean.class)) {
                Object bean = method.invoke(clazz.getDeclaredConstructor().newInstance());
                beanFactory.registerSingleton(method.getName(), bean);
            }
        }
    }
}
