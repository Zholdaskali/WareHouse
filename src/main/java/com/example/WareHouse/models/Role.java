package com.example.WareHouse.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<User> user;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
