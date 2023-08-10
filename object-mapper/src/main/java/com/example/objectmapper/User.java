package com.example.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private  String name;
    private  int age;
    @JsonProperty("phone_number")
    private  String phoneNumber;

    public User(){
        this.name = null;
        this.age = 0;
        this.phoneNumber = null;
    }

    public User(String name, int age, String phoneNumber){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
