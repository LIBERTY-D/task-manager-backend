package com.daniel.app.TaskManager.Profile.model;


import com.daniel.app.TaskManager.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
@Table(name = "profile")
public class ProfileModel {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private byte[] profileImage;
    @Column(name = "description")
    private String desc;

    public UserModel getUser() {
        return user;
    }

    public ProfileModel setUser(UserModel user) {
        this.user = user;
        return this;
    }

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public ProfileModel() {
    }


    public ProfileModel(String desc, Integer id, byte[] profileImage,
                        UserModel user) {
        this.desc = desc;
        this.id = id;
        this.profileImage = profileImage;
        this.user =  user;
    }

    public String getDesc() {
        return desc;
    }

    public ProfileModel setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ProfileModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public ProfileModel setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
        return this;
    }
}