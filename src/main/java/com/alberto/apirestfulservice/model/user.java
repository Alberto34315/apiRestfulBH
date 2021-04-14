package com.alberto.apirestfulservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "pass")
    private String pass;

    @NotBlank
    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = {"creator"}, allowSetters = true)
    private List<training> lt;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = {"creator"}, allowSetters = true)
    private List<exercise> le;
    
    @OneToMany(mappedBy = "idu", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = {"idu"}, allowSetters = true)
    private List<records> lrecords;
  
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "Friendship", joinColumns = @JoinColumn(name = "owner_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    @JsonIgnoreProperties(value = {"friends"}, allowSetters = true)
    private Set<user> friends;

    public List<exercise> getLe() {
        return le;
    }

    public void setLe(List<exercise> le) {
        if (le == null) {
            le = new ArrayList<exercise>();
        }
        this.le = le;
        for (exercise e : le) {
            e.setCreator(this);
        }
    }

    public List<training> getLt() {
        return lt;
    }

    public void setLt(List<training> lt) {
        if (lt == null) {
            lt = new ArrayList<training>();
        }
        this.lt = lt;
        for (training training : lt) {
            training.setCreator(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<user> getFriends() {
        return friends;
    }

    public void setFriends(Set<user> friends) {
        if (friends == null) {
            friends = new HashSet<user>();
        }
        this.friends = friends;
        for (user friend : friends) {
            Set<user> list = friend.getFriends();
            if (list == null) {
                list = new HashSet<user>();
            }
            if (!list.contains(this)) {
                list.add(this);
            }
        }
    }

    public List<records> getLrecords() {
        return lrecords;
    }

    public void setLrecords(List<records> lrecords) {
      
        if (lrecords == null) {
            lrecords = new ArrayList<records>();
        }
        this.lrecords = lrecords;
        for (records record : lrecords) {
            record.setIdu(this);
        }
    }

    @Override
    public String toString() {
        return "user [id=" + id + ", name=" + name + ", email=" + email + ", pass=" + pass + ", avatar=" + avatar + "]";
    }

}
