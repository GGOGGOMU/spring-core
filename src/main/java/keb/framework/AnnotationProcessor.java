package keb.framework;

import keb.framework.annotations.Bean;
import keb.framework.annotations.Component;
import keb.framework.annotations.Configuration;

import java.lang.reflect.Method;
import java.util.List;

import static keb.framework.BeanUtils.convertBeanName;

public class AnnotationProcessor {

    private BeanFactory beanFactory;
    private LocationScanner locationScanner;


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

        for (Class<?> configClass : configurationClasses) {
            annotatedConfiguration(configClass);
        }
    }

    private void annotatedComponent(Class<?> clazz) throws Exception {
        if (clazz.isAnnotationPresent(keb.framework.annotations.Component.class)) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            beanFactory.registerSingleton(convertBeanName(clazz.getName().trim()), instance);
        }
    }

    private void annotatedConfiguration(Class<?> configClass) throws Exception {
        for (Method method : configClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Bean.class)) {
                Object bean = method.invoke(configClass.newInstance());
                beanFactory.registerSingleton(method.getName(), bean);
            }
        }
    }
}
