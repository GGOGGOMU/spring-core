package framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 * Scanner로 읽은 파일들을 인스턴스화 시키는 클래스
 * 스프링프레임워크의 DefaultListableBeanFactory, DefaultSingletonBeanRegistry 코드 참조
 * */

public class BeanFactory {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    public void registerSingleton(String beanName, Object singletonObject) throws IllegalStateException {
        synchronized(this.singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject + "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
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
