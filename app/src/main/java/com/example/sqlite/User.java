package com.example.sqlite;

public class User {
    private String name,age;
    private int id;

    public User() {
    }

    public User(String name, String age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
}
