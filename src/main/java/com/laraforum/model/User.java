package com.laraforum.model;

import com.laraforum.model.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    @Email(message = "please provide valid email")
    private String email;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String passWord;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    private boolean isActivated = false;

    public void setRoles(Role newRole) {
        // TODO ??? add string cnnot work ?
        this.roles.add(newRole);
    }


}