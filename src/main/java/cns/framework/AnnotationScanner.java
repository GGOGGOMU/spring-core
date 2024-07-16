package cns.framework;

import cns.annotations.CNSBean;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnnotationScanner {
    public List<String> read(String basePackage) {
        List<String> classNames = new ArrayList<>();

        Set<Class<?>> classes = getClasses(basePackage);
        for (Class<?> clazz : classes) {
            classNames.add(clazz.getName());
        }

        return classNames;
    }

    private Set<Class<?>> getClasses(String basePackage) {
        Reflections reflections = new Reflections(basePackage, Scanners.TypesAnnotated, Scanners.MethodsAnnotated);
        return reflections.getTypesAnnotatedWith(CNSBean.class);
    }
}
