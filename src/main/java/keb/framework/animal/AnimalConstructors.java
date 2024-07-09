package keb.framework.animal;


import keb.framework.annotations.Component;

@Component
public class AnimalConstructors {

    public Cat voidCat(){
        return new Cat();
    }

    public Dog voidDog(){
        return new Dog();
    }

    public Panda voidPanda(){
        return new Panda();
    }

    public Cat newCat(){
        return new Cat("고냠니", 1000);
    }

    public Dog newDog(){
        return new Dog("감마지", 2453);
    }

    public Panda newPanda(){
        return new Panda("팜다", 37872);
    }

    public Cat meowCat(){
        return new Cat("야옹");
    }

    public Dog barkDog(){
        return new Dog("망망");
    }

    public Panda eatPanda(){
        return new Panda("냠냠");
    }

}
