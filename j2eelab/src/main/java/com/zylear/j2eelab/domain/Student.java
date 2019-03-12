package com.zylear.j2eelab.domain;

public class Student {

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {

    }

    private Integer id=2;

    private String name="sss";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}