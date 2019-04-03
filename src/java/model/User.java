/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 * User class
 *
 * @author Alejandro Asensio
 * @version 1.1, 2019-02-13
 */
public class User {

    // Atributos
    private String name;
    private String password;
    private String role;
    private String email;
    private String address;
    private String dni;

    // Constructors
    public User(String name, String password, String role, String email, String address, String dni) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.address = address;
        this.dni = dni;
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.role = "basic"; // default role
    }

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    // Accessors: setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Overridden method to compare two User instances only by their names.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Translate a User object to a String. Name and password are separated
     * by a colon ':'.
     *
     * @return String representation of a User object
     */
    @Override
    public String toString() {
        return String.format("%s;%s;%s;\n", name, password, role);
    }

}
