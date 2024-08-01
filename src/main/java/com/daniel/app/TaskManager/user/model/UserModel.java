package com.daniel.app.TaskManager.user.model;

import com.daniel.app.TaskManager.Profile.model.ProfileModel;
import com.daniel.app.TaskManager.task.model.TaskModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel  implements UserDetails {// for spring  Authentication AND Authorization implement UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;


    @Column(unique = true)
    private  String email;


    @Column(unique = true)

    private  String  username;

    @JsonBackReference
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL )
    private ProfileModel profile;


    public List<TaskModel> getTasks() {
        return tasks;
    }

    public UserModel setTasks(List<TaskModel> taskModels) {
        this.tasks = taskModels;
        return this;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch =
            FetchType.EAGER,orphanRemoval = true)
    private List<TaskModel> tasks;
    public ProfileModel getProfile() {
        return profile;
    }

    public UserModel setProfile(ProfileModel profile) {
        this.profile = profile;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserModel setRole(Role role) {
        this.role = role;
        return this;
    }

    @Enumerated(value = EnumType.STRING)
    private Role role=Role.USER;

    public UserModel() {
    }

    public  UserModel( String firstName, String lastName, String email,
                       String username,String password,Role role,
                       ProfileModel profile,List<TaskModel> tasks){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.username = username;
        this.role = role;
        this.profile = profile;
        this.tasks = tasks;

    }



    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public UserModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getFirstName() {
        return firstName;
    }

    public UserModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }
    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


}
