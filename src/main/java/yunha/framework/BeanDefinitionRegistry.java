
package yunha.framework;

public interface BeanDefinitionRegistry {
    void registerSingleton(String beanName, Object singletonObject);
}
