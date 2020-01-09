package com.katamlek.training.skillomatic.skill.service.converter;

import com.katamlek.training.skillomatic.helper.EntityDtoConverter;
import com.katamlek.training.skillomatic.skill.dto.SkillDto;
import com.katamlek.training.skillomatic.skill.persistence.model.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillConverter implements EntityDtoConverter<Skill, SkillDto> {

    @Override
    public SkillDto convert(Skill skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getUid());
        skillDto.setSkillName(skill.getSkillName());
        return skillDto;
    }

    @Override
    public Skill convert(SkillDto skillDto) {
        Skill skill = new Skill();
        skill.setUid(skillDto.getId());
        skill.setSkillName(skillDto.getSkillName());
        return skill;
    }

}
