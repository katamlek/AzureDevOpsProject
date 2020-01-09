package com.katamlek.training.skillomatic.skills;

import com.katamlek.training.skillomatic.skill.service.SkillService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.katamlek.training.skillomatic.skills.SkillTestHelper.SKILL_API_CONTROLLER;

@Profile(SKILL_API_CONTROLLER)
@Configuration
public class SkillTestConfiguration {

    @Primary
    @Bean
    public SkillService skillService() {
        return Mockito.mock(SkillService.class);
    }

}
