package model;

public class Player {
    private int id;
    private String name;
    private int age;
    private String username;
    private String password;
    private String games;
    private String Image;

    public Player(String name, int age, String games, String image) {
        this.name = name;
        this.age = age;
        this.games = games;
        Image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Player(String name, int age, String username, String password, String games) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.games = games;
    }

    public Player(int id, String name, int age, String image) {
        this.id = id;
        this.name = name;
        this.age = age;
        Image = image;
    }

    public Player() {
    }

    public Player(String name, int age, String image) {
        this.name = name;
        this.age = age;
        Image = image;
    }

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

    public int getAge() {
        return age;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", Image='" + Image + '\'' +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
