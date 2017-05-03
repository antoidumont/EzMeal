package com.lsinf1225.groupe_s.ezmeal;

import java.io.Serializable;

/**
 * Write a description of class Ingredient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Utilisateur implements Serializable {
    private String username;
    private String mail;
    private String mdp;
    private String sex;
    private String name;
    private String firstname;
    private int age;
    private String address;
    private String language;

    public Utilisateur(String username, String mail, String mdp, String sex, String name, String firstname, int age, String address, String language) {
        this.username = username;
        this.mail = mail;
        this.mdp = mdp;
        this.sex = sex;
        this.name = name;
        this.firstname = firstname;
        this.age = age;
        this.address = address;
        this.language = language;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return this.name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}