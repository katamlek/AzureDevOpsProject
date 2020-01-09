package com.katamlek.training.skillomatic.skill.persistence.model;

import com.katamlek.training.skillomatic.helper.BaseEntity;
import com.katamlek.training.skillomatic.person.persistence.models.PersonProfile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "PersonSkill")
@Table(name = "person_skill")
public class PersonSkill implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT(20) UNSIGNED")
    private Long id;

    @Column(name = "uid", columnDefinition = "BINARY(16)", nullable = false)
    private UUID uid;

    @NotNull
    @Column(name = "skill_score", columnDefinition = "BIGINT(10) UNSIGNED", nullable = false)
    private Integer skillScore;

    @ManyToOne
    private PersonProfile personProfile;

    @ManyToOne
    private Skill skill;

    public PersonSkill() {
    }

    public PersonSkill(UUID uid, @NotNull Integer skillScore, PersonProfile personProfile, Skill skill) {
        this.uid  = uid;
        this.skillScore = skillScore;
        this.personProfile = personProfile;
        this.skill = skill;
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

    public Integer getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(Integer skillScore) {
        this.skillScore = skillScore;
    }

    public PersonProfile getPersonProfile() {
        return personProfile;
    }

    public void setPersonProfile(PersonProfile personProfile) {
        this.personProfile = personProfile;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonSkill that = (PersonSkill) o;
        return id.equals(that.id) &&
                uid.equals(that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid);
    }

}
