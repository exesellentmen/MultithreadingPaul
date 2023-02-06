package com.paul.startclass.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students", schema = "sova", catalog = "")
public class Student {
    private int id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
