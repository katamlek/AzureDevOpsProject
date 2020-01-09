package com.katamlek.training.skillomatic.person.api.v1.controllers;

import com.katamlek.training.skillomatic.person.dto.PersonProfileDto;
import com.katamlek.training.skillomatic.person.persistence.models.PersonProfile;
import com.katamlek.training.skillomatic.skill.persistence.model.PersonSkill;
import com.katamlek.training.skillomatic.skill.persistence.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

public class TestHelper {

    public static final String PROFILE_API_CONTROLLER = "test-person-profile-api-controller";
    public static final String PROFILE_API_E2E_TEST = "test-person-profile-api-e2e";

    public static Page<PersonProfileDto> createPersons(Pageable pageable) {
        List<PersonProfileDto> persons = new LinkedList<>();
        for (int i = 0; i < pageable.getPageSize(); i++) {
            persons.add(doCreatePerson(i));
        }
        return new PageImpl<>(persons, pageable, -1);
    }

    public static PersonProfileDto doCreatePerson(int index) {

        PersonProfileDto personProfileDto = new PersonProfileDto();
        personProfileDto.setId(UUID.randomUUID());
        personProfileDto.setFirstName("Tester-" + index);
        personProfileDto.setLastName("Dude-" + index);
        personProfileDto.setEmail("tester.dude" + index + "@random-domain.com");

        PersonProfile testPersonProfile = new PersonProfile();
        testPersonProfile.setFirstName("Test");
        testPersonProfile.setLastName("Test");

        Set<PersonSkill> personSkills = new HashSet<>();
        for (int i = 1; i <= 3; i++) {
            personSkills.add(new PersonSkill(UUID.randomUUID(), i, testPersonProfile, new Skill("test" + i)));
        }

        return personProfileDto;
    }
}
