package com.katamlek.training.skillomatic.person.dto;

import com.katamlek.training.skillomatic.helper.BaseDto;

import java.util.Objects;
import java.util.UUID;

public class PersonSkillDto implements BaseDto {

    private UUID id;
    private String skillName;
    private Integer skillScore;
    private UUID skillId;
    private UUID personProfileId;

    public PersonSkillDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(Integer skillScore) {
        this.skillScore = skillScore;
    }

    public UUID getSkillId() {
        return skillId;
    }

    public void setSkillId(UUID skillId) {
        this.skillId = skillId;
    }

    public UUID getPersonProfileId() {
        return personProfileId;
    }

    public void setPersonProfileId(UUID personProfileId) {
        this.personProfileId = personProfileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonSkillDto that = (PersonSkillDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(skillName, that.skillName) &&
                Objects.equals(skillScore, that.skillScore) &&
                Objects.equals(skillId, that.skillId) &&
                Objects.equals(personProfileId, that.personProfileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillName, skillScore, skillId, personProfileId);
    }

}
