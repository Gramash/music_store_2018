package com.epam.garmash.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

public class User {

    private String name;
    private String lastName;
    private String pass;
    private String email;
    private String avatar;
    private boolean subscription;

    public User(String name, String lastName, String email, String pass, String avatar, boolean subscription) {
        this.name = name;
        this.lastName = lastName;
        this.pass = pass;
        this.email = email;
        this.avatar = avatar;
        this.subscription = subscription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final User other = (User) object;
        return new EqualsBuilder()
                .append(other.email, email)
                .append(other.pass, pass)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, pass);
    }
}