package com.example.myapplication;

public class Games {
    private int score;
    private String name;
    private int id;
    private String date;

    public Games(int score, String name, int id, String date) {
        this.score = score;
        this.name = name;
        this.id = id;
        this.date = date;
    }

    public Games(int score, String name, String date) {
        this.score = score;
        this.name = name;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
