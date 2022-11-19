package com.github.jj_sallo.recordstore.entity;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user", nullable = false, unique = true)
    public Long id;

    @Column(name="user_email")
    public String email;
}
