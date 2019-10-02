package com.vkozlov.simplenotepad.domain;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String text;
    private String priority;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Note() {
    }

    public Note(String text, String priority, User user) {
        this.text = text;
        this.priority = priority;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
