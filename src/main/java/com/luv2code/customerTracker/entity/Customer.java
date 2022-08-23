package com.luv2code.customerTracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // to deal with conversion exception, failed so far
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @NotNull(message = "Required field missing")
    @Pattern(regexp = "^[a-zA-Z ]", message = "Invalid character")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Required field missing")
    @Pattern(regexp = "^[a-zA-Z ]", message = "Invalid character")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Required field missing")
    @Email
    //@Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "Invalid Email")
    @Column(name = "email")
    private String email;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", lastModified=" + lastModified +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @PrePersist // executed before creating record in database
    protected void setCreatedOn(){
        this.createdOn = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }

    @PreUpdate  // executed before everytime saving into database
    protected void setLastModified(){
        this.lastModified = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { // required for doing update on existing record objects, for hidden field id
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
