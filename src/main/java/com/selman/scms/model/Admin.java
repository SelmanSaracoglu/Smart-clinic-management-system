

//JPA → Java Persistence API ile veritabanı tablosuna bağlanır (@Entity, @Id, @Column, vs.)

//Validation → Alanlara giriş kuralları koyar (örneğin @NotNull, @Email, @Size)

//JSON → @JsonProperty gibi Jackson anotasyonlarıyla JSON gösterimini özelleştirir
        //Örneğin, password alanı dışarıya gösterilmesin (sadece yazılabilsin): @JsonProperty(access = WRITE_ONLY)

package com.selman.scms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @JsonProperty("username")
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // hide from JSON responses
    private String password;

    @Column(unique = true)
    private String email;

    private String role = "ADMIN";

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
