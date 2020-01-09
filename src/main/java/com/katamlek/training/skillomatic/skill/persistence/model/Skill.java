package com.katamlek.training.skillomatic.skill.persistence.model;

import com.katamlek.training.skillomatic.helper.BaseEntity;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Skill")
@Table(name = "skill")
public class Skill implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT(20) UNSIGNED")
    private Long id;

    @Column(name = "uid", columnDefinition = "BINARY(16)", nullable = false)
    private UUID uid;

    @Column(name = "skill_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String skillName;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.PERSIST)
    private List<PersonSkill> personSkills = new ArrayList<>();

    public Skill() {
    }

    public Skill(String skillName) {
        this.skillName = skillName;
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

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public List<PersonSkill> getPersonSkills() {
        return personSkills;
    }

    public void setPersonSkills(List<PersonSkill> personSkills) {
        this.personSkills = personSkills;
    }

}
