package cns.framework;

public interface BeanDefinitionRegistry {
    void registerSingleton(String beanName, Object singletonObject);
}
