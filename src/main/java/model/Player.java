package model;

public class Player {
    private int id;
    private String name;
    private int age;
    private String games;
    private String Image;

    public Player(String name, int age, String games, String image) {
        this.name = name;
        this.age = age;
        this.games = games;
        Image = image;
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
