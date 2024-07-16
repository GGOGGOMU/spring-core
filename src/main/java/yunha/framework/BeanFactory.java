package yunha.framework;


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class BeanFactory implements BeanDefinitionRegistry {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    private final Map<String, Object> prototypeObjects = new ConcurrentHashMap<>(256);

    public void getRegisteredBean() {
        System.out.println("----------------Registered Singleton Bean names ------------");
        Collection<String> singletonObjects = this.singletonObjects.keySet();

        for(String value : singletonObjects) {
            System.out.println(value);
        }

        System.out.println("----------------Registered Prototype Bean names ------------");
        Collection<String> prototypeObjects = this.prototypeObjects.keySet();

        for(Object value : prototypeObjects) {
            System.out.println(value);
        }
    }

    @Override
    public void registerPrototype(String beanName, Object prototypeObject) throws IllegalStateException {
        this.prototypeObjects.put(beanName, prototypeObject);
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
