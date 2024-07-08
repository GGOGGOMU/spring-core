<<<<<<<< HEAD:src/main/java/cns/users/Dog.java
package cns.users;
========
package yunha.model;
>>>>>>>> yunha:src/main/java/yunha/model/Dog.java

public class Dog {
    private String name;
    private int age;

    public Dog() {
        this.name = "Dog";
        this.age = 1;
    }

    public Dog(String name, int age) {
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
