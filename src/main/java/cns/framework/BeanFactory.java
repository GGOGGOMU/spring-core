package cns.framework;


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class BeanFactory implements BeanDefinitionRegistry {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    public void getRegisteredBean() {
        System.out.println("----------------Registered Bean names ------------");
        Collection<Object> values = singletonObjects.values();

        for(Object value : values) {
            System.out.println(value.getClass());
        }
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) throws IllegalStateException {
        synchronized(this.singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject + "] under test.bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            } else {
                this.addSingleton(beanName, singletonObject);
            }
        }
    }


    protected void addSingleton(String beanName, Object singletonObject) {
        synchronized(this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
        }
    }

}
