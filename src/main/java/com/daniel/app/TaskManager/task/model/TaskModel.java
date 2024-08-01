package com.daniel.app.TaskManager.task.model;


import com.daniel.app.TaskManager.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity()
@Table(name = "task")
public class TaskModel {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column()
    String title;
    @Column(name = "description")

    String desc;
    @Column()
    Date dueDate;
    @Column()

    String[] tags;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserModel user;

    public TaskModel setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public TaskModel setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
        return this;
    }

    private Date completedOn;
    public boolean isCompleted() {
        return isCompleted;
    }

    public TaskModel setIsCompleted(boolean isCompleted) {
        this.isCompleted =isCompleted;
        return this;
    }

    private boolean isCompleted=false;
    public TaskModel(){

    }

    public UserModel getUser() {
        return user;
    }

    public TaskModel setUser(UserModel user) {
        this.user = user;
        return this;
    }


    public TaskModel(String title, String desc, Date dueDate, String[] tags,
                     boolean isCompleted, UserModel user) {
        this.title = title;
        this.desc = desc;
        this.dueDate = dueDate;
        this.tags = tags;
        this.user = user;
        this.isCompleted = isCompleted;
    }

    public String getDesc() {
        return desc;
    }

    public TaskModel setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public TaskModel setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public TaskModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String[] getTags() {
        return tags;
    }

    public TaskModel setTags(String[] tags) {
        this.tags = tags;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TaskModel setTitle(String title) {
        this.title = title;
        return this;
    }
}
