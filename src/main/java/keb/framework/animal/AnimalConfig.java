package keb.framework.animal;

import keb.framework.annotations.Bean;
import keb.framework.annotations.Configuration;

@Configuration
public class AnimalConfig {

    @Bean
    public Cat configCat(){
        return new Cat();
    }

    @Bean
    public Dog configDog(){
        return new Dog();
    }

    @Bean
    public Panda configPanda(){
        return new Panda();
    }
}
