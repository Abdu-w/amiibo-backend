package com.capstone.project.model;

import javax.persistence.*;

@Entity
@Table(name= "comments")
public class Comments {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "comments")
    private String comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
