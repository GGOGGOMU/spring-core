package keb.framework.user;

public class Panda {
    private String name;
    private int age;

    public Panda() {
    }

    public Panda(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Panda(String eatPanda) {
        System.out.println(eat(eatPanda));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String eat(String eat) {
        return eat;
    }
}
