package cns.annotation_user;

import cns.annotations.CNSBean;
import cns.users.Bird;
import cns.users.Cat;
import cns.users.Dog;

public class CnaAnimalFactory {

    @CNSBean
    public Bird getBird() {
        return new Bird();
    }

    @CNSBean
    public Cat getCat() {
        return new Cat();
    }

    @CNSBean
    public Dog getDog() {
        return new Dog();
    }
}