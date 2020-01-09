package com.katamlek.training.skillomatic.person.persistence.models;

import com.katamlek.training.skillomatic.helper.BaseEntity;
import com.katamlek.training.skillomatic.skill.persistence.model.PersonSkill;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "PersonProfile")
@Table(name = "person_profile")
public class PersonProfile implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT(20) UNSIGNED")
    private Long id;

    @NotNull
    @Column(name = "uid", columnDefinition = "BINARY(16)", nullable = false)
    private UUID uid;

    @NotNull
    @Column(name = "first_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "email_address", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String emailAddress;

    @OneToMany(mappedBy = "personProfile", cascade = CascadeType.ALL)
    private Set<PersonSkill> personSkills = new HashSet<>();

    public PersonProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<PersonSkill> getPersonSkills() {
        return personSkills;
    }

    public void setPersonSkills(Set<PersonSkill> personSkills) {
        this.personSkills = personSkills;
    }

}
