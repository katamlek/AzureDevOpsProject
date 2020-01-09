package com.katamlek.training.skillomatic.person.dto;

import com.katamlek.training.skillomatic.helper.BaseDto;

import java.util.Objects;
import java.util.UUID;

public class PersonProfileDto implements BaseDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    public PersonProfileDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "PersonProfileDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonProfileDto personProfileDto = (PersonProfileDto) o;
        return Objects.equals(id, personProfileDto.id) &&
                Objects.equals(firstName, personProfileDto.firstName) &&
                Objects.equals(lastName, personProfileDto.lastName) &&
                Objects.equals(email, personProfileDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

}
