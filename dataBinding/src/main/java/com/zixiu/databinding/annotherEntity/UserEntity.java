package com.zixiu.databinding.annotherEntity;

/**
 * Author: Snail
 * Time:  2021/1/25 6:16 PM
 * FileName:  UserEntity
 * 简介：
 */
public class UserEntity {

    private int grade;

    private int age;

    private String className;

    public UserEntity(int grade, int age, String className) {
        this.grade = grade;
        this.age = age;
        this.className = className;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
