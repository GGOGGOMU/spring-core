package cns.framework;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

/**
 * description
 * user 하위 디렉터리에 있는 클래스를 읽어 들이는 클래스
 * SpringFactoriesLoader 참고
 */
public class LocationScanner {


    public List<String> read(String basePackage) {
        List<String> classNames = new ArrayList<>();

        Set<Class<?>> classes = getClasses(basePackage);
        for (Class<?> clazz : classes) {
            classNames.add(clazz.getName());
        }

        return classNames;

    }

    private Set<Class<?>> getClasses(String basePackage) {
        Reflections reflections = new Reflections(basePackage, Scanners.SubTypes.filterResultsBy(s -> true));
        return reflections.getSubTypesOf(Object.class);
    }

}
