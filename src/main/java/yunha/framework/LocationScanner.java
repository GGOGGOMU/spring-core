package yunha.framework;


import org.reflections.scanners.SubTypesScanner;
import yunha.annotation.Bean;
import org.reflections.Reflections;
import yunha.annotation.Component;
import yunha.annotation.Configuration;
import yunha.annotation.Scope;
import yunha.enums.ScopeType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * description
 * test.user 하위 디렉터리에 있는 클래스를 읽어 들이는 클래스
 * */
public class LocationScanner {
    private final BeanFactory beanFactory;

    public LocationScanner(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object classScanner(String... basePackages) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(basePackages == null || basePackages.length == 0)
            return new HashSet<Class>();

        Set<Class> classes = new HashSet<Class>();

        for(String basePackageName : basePackages) {
            Reflections reflections = new Reflections(basePackageName, new SubTypesScanner(false));
            classes.addAll(reflections.getSubTypesOf(Object.class));
        }

        if(classes == null)
            return null;

        for(Class clazz : classes) {
            beanFactory.registerSingleton(clazz.getSimpleName(), clazz.getDeclaredConstructor().newInstance());
        }

        return classes;
    }

    public void methodScanner(String... basePackages) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(basePackages == null || basePackages.length == 0)
            return;

        for(String basePackageName : basePackages) {
            Reflections reflections = new Reflections(basePackageName, new SubTypesScanner(false));
            Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

            if(classes == null)
                continue;

            for(Class clazz : classes) {
                Method[] methods = clazz.getDeclaredMethods();
                Object instance = clazz.getDeclaredConstructor().newInstance();
                if(methods != null && methods.length > 0) {
                    for(Method method : methods) {
                        if (Modifier.isPublic(method.getModifiers())) {
                            beanFactory.registerSingleton(method.getName(), method.invoke(instance));
                        }
                    }
                }
            }
        }

    }

    public void annotationScanner(String rootPackagePath) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Reflections reflections = new Reflections(rootPackagePath, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        if(classes == null)
            return;

        ScopeType scopeType = null;
        for(Class clazz : classes) {
            if(clazz.isAnnotationPresent(Component.class)) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                scopeType = checkClassIsScopeAnnotationPresent(clazz);
                registerBean(clazz.getSimpleName(), instance, scopeType);
            } else if(clazz.isAnnotationPresent(Configuration.class)) {
                Method[] methods = clazz.getDeclaredMethods();
                if(methods != null && methods.length > 0) {
                    for(Method method : methods) {
                        if(method.isAnnotationPresent(Bean.class.asSubclass(Bean.class))) {
                            Bean beanAnnotation = method.getAnnotation(Bean.class);
                            String name = beanAnnotation.value().equals("") ? method.getName() : beanAnnotation.value();
                            Object instance = method.invoke(clazz.getDeclaredConstructor().newInstance());
                            scopeType = checkMethodIsScopeAnnotationPresent(method);
                            registerBean(name, instance, scopeType);
                        }
                    }
                }
            }
        }
    }

    private void registerBean(String name, Object instance, ScopeType scopeType) {
        if(scopeType == ScopeType.SINGLETON) {
            beanFactory.registerSingleton(name, instance);
        } else if (scopeType == ScopeType.PROTOTYPE) {
            beanFactory.registerPrototype(name, instance);
        }
    }

    private ScopeType checkClassIsScopeAnnotationPresent(Class clazz) {
        ScopeType scopeType = ScopeType.SINGLETON;
        if(clazz.isAnnotationPresent(Scope.class)) {
            Scope scopeAnnotation = (Scope) clazz.getAnnotation(Scope.class);
            scopeType = scopeAnnotation.type();
        }
        return scopeType;
    }

    private ScopeType checkMethodIsScopeAnnotationPresent(Method method) {
        ScopeType scopeType = ScopeType.SINGLETON;
        if(method.isAnnotationPresent(Scope.class)) {
            Scope scopeAnnotation = (Scope) method.getAnnotation(Scope.class);
            scopeType = scopeAnnotation.type();
        }
        return scopeType;
    }
}
