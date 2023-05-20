package model;

public class User {
    private int id;
    private String name;
    private String username;
    private int age;
    private String password;

    public User(int id, String name, String username, int age, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.age = age;
        this.password = password;
    }

    public User(String name, int age,String username, String password) {
        this.name = name;
        this.username = username;
        this.age = age;
        this.password = password;
    }
    public User (){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
