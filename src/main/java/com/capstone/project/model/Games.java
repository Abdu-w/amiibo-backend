package com.capstone.project.model;


import javax.persistence.*;

@Entity
@Table(name= "game_title")
public class Games {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;


    @Column(name = "game_name")
    private String game_name;

    @Column(name = "release_date")
    private String release_date;

    @Column(name = "summary")
    private String summary;

    public Games() {
    }

    public Games(String game_name, String release_date, String summary) {
        this.game_name = game_name;
        this.release_date = release_date;
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
