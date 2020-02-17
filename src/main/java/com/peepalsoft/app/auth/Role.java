package com.peepalsoft.app.auth;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.peepalsoft.app.entity.Users;

import javax.persistence.*;
import java.util.Collection;

/*
POWERED BY PEEPALSOFT - SHISHIR KARKI
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Collection<Users> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }
}
