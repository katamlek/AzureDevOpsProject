package com.katamlek.training.skillomatic.skill.service;

import com.katamlek.training.skillomatic.skill.dto.SkillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SkillService {

    Page<SkillDto> getSkills(Pageable pageable);

    SkillDto getSkillByUid(UUID skillId);

    SkillDto addSkill(SkillDto skillDto);

    SkillDto updateSkill(SkillDto skillDto);

    SkillDto deleteSkill(UUID skillId);

}
