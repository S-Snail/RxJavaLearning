package com.zixiu.databinding.entity;

/**
 * Author: Snail
 * Time:  2021/1/25 4:22 PM
 * FileName:  UserEntity
 * 简介：
 */
public class UserEntity {

    private int age;
    private String name;
    private String lastName = "LastName";

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserEntity(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
