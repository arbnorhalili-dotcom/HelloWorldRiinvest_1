package com.riinvest.helloworldriinvest_1;

public class User {
    private int Id;
    private String Name;
    private String Surname;
    private String Email;

    public User(int id, String name, String surname, String email) {
        Id = id;
        Name = name;
        Surname = surname;
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
