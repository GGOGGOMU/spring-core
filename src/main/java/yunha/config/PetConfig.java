package yunha.config;

import yunha.model.Cat;
import yunha.model.Dog;

public class PetConfig {

    public Cat myCat() {
        return new Cat("Kitty", 1);
    }

    public Dog myDog() {
        return new Dog("Puppy", 2);
    }
}
