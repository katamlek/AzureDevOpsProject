package com.katamlek.training.skillomatic.skill.dto;

import com.katamlek.training.skillomatic.helper.BaseDto;

import java.util.Objects;
import java.util.UUID;

public class SkillDto implements BaseDto {

    private UUID id;
    private String skillName;

    public SkillDto() {
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

    @Override
    public String toString() {
        return "SkillDto{" +
                "id=" + id +
                ", name='" + skillName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDto skillDto = (SkillDto) o;
        return Objects.equals(id, skillDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
