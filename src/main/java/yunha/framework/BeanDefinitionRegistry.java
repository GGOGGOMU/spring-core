
package yunha.framework;

public interface BeanDefinitionRegistry {
    void registerPrototype(String beanName, Object prototypeObject) throws IllegalStateException;

    void registerSingleton(String beanName, Object singletonObject);
}
