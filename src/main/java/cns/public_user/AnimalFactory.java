package cns.public_user;

import cns.users.Bird;
import cns.users.Cat;
import cns.users.Dog;

public class AnimalFactory {


    public Bird getBird() {
        return new Bird();
    }

    public Cat getCat() {
        return new Cat();
    }

    public Dog getDog() {
        return new Dog();
    }
}
