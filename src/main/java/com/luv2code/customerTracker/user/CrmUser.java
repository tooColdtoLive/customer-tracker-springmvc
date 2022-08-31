package com.luv2code.customerTracker.user;

import com.luv2code.customerTracker.constraint.FieldMatch;
import com.luv2code.customerTracker.constraint.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class CrmUser {

    @NotNull(message = "Required field missing")
    @Size(min = 1, message = "Required field missing")
    @Pattern(regexp = "^[a-zA-Z1-9]+", message = "Invalid character")
    private String username;

    @NotNull(message = "Required field missing")
    @Size(min = 1, message = "Required field missing")
    private String password;

    @NotNull(message = "Required field missing")
    @Size(min = 1, message = "Required field missing")
    private String matchingPassword;

    @NotNull(message = "Required field missing")
    @Size(min = 1, message = "Required field missing")
    @Pattern(regexp = "[a-zA-Z ]+", message = "Invalid character")
    private String firstName;

    @NotNull(message = "Required field missing")
    @Size(min = 1, message = "Required field missing")
    @Pattern(regexp = "[a-zA-Z ]+", message = "Invalid character")
    private String lastName;

    @NotNull(message = "Required field missing")
    @Size(min = 1, message = "Required field missing")
    @ValidEmail
    private String email;

    public CrmUser() {}

    public CrmUser(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "CrmUser{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
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
