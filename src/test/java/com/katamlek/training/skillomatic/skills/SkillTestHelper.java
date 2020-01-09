package com.katamlek.training.skillomatic.skills;

import com.katamlek.training.skillomatic.skill.dto.SkillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class SkillTestHelper {

    public static final String SKILL_API_CONTROLLER = "test-skill-api-controller";
    public static final String SKILL_API_E2E_TEST = "test-skill-api-e2e";

    public static Page<SkillDto> createSkills(Pageable pageable) {
        List<SkillDto> skillDtos = new LinkedList<>();
        for (int i = 0; i < pageable.getPageSize(); i++) {
            skillDtos.add(doCreateSkill(i));
        }
        return new PageImpl<>(skillDtos, pageable, -1);
    }

    public static SkillDto doCreateSkill(int index) {

        SkillDto skillDto = new SkillDto();
        skillDto.setId(UUID.randomUUID());
        skillDto.setSkillName("Test-" + index);

        return skillDto;
    }
}
