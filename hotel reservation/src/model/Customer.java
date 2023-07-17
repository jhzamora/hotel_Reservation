package model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String firstName, lastName, email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        if (!validateEmail()){
            throw new IllegalArgumentException("Invalid email address: " + email);
        }
    }

    private Boolean validateEmail(){
        String emailRegex = "[a-z0-9]+@[a-z]+\\.com";
        return Pattern.compile(emailRegex).matcher(email).matches();

    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getEmail().equals(customer.getEmail());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getEmail());
    }
}
