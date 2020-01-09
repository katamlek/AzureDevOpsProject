package com.katamlek.training.skillomatic.person.service;

import com.katamlek.training.skillomatic.person.dto.PersonProfileDto;
import com.katamlek.training.skillomatic.person.dto.PersonSkillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;


public interface PersonService {

    Page<PersonProfileDto> getPersonProfiles(Pageable pageable);

    PersonProfileDto getPersonProfileByUid(UUID personProfileId);

    PersonProfileDto addPersonProfile(PersonProfileDto personProfileDto);

    PersonProfileDto updatePersonProfile(PersonProfileDto personProfileDto);

    PersonProfileDto deletePersonProfile(UUID personProfileDto);

    Set<PersonSkillDto> getPersonProfileSkillsByUid(UUID personProfileId);

    PersonSkillDto deletePersonSkill(UUID personSkillId);

    PersonSkillDto addPersonSkill(PersonSkillDto personSkillDto);

}
