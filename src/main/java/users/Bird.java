package users;

public class Bird {
    private String name;
    private int age;

    public Bird() {
        this.name = "Bird";
        this.age = 1;
    }

    public Bird(String name, int age) {
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
