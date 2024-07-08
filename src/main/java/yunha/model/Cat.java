<<<<<<<< HEAD:src/main/java/cns/users/Cat.java
package cns.users;
========
package yunha.model;
>>>>>>>> yunha:src/main/java/yunha/model/Cat.java

public class Cat {
    private String name;
    private int age;

    public Cat() {
        this.name = "Cat";
        this.age = 1;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
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
}
