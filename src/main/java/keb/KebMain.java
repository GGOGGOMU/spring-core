package keb;


import keb.framework.AnnotationProcessor;
import keb.framework.BeanFactory;

public class KebMain {
    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new BeanFactory();

        AnnotationProcessor annotationProcessor = new AnnotationProcessor(beanFactory);

        annotationProcessor.processAnnotations("keb.framework.animal");

        beanFactory.getBeans().forEach(System.out::println);

    }
}
