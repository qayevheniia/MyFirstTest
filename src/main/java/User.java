public class User {
    private String name;
    private int age;
    private Boolean isRegistered;

    public User(String name, Integer age, Boolean isRegistered) {
        this.name = name;
        this.age = age;
        this.isRegistered = isRegistered;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
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
