package com.katamlek.training.skillomatic.person.service.impl;

import com.katamlek.training.skillomatic.helper.DataUtils;
import com.katamlek.training.skillomatic.person.dto.PersonProfileDto;
import com.katamlek.training.skillomatic.person.dto.PersonSkillDto;
import com.katamlek.training.skillomatic.person.persistence.models.PersonProfile;
import com.katamlek.training.skillomatic.person.persistence.repository.PersonProfileRepository;
import com.katamlek.training.skillomatic.person.service.PersonService;
import com.katamlek.training.skillomatic.person.service.converter.PersonProfileConverter;
import com.katamlek.training.skillomatic.skill.persistence.model.PersonSkill;
import com.katamlek.training.skillomatic.skill.persistence.model.Skill;
import com.katamlek.training.skillomatic.skill.persistence.repository.PersonSkillRepository;
import com.katamlek.training.skillomatic.skill.persistence.repository.SkillRepository;
import com.katamlek.training.skillomatic.skill.service.converter.PersonSkillConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultPersonService implements PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultPersonService.class);

    @Autowired
    private final PersonProfileConverter personProfileConverter;

    @Autowired
    private PersonProfileRepository personProfileRepository;

    @Autowired
    private PersonSkillRepository personSkillRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private PersonSkillConverter personSkillConverter;

    public DefaultPersonService(
            PersonProfileRepository personProfileRepository, PersonProfileConverter personProfileConverter) {
        this.personProfileRepository = personProfileRepository;
        this.personProfileConverter = personProfileConverter;
    }

    @Override
    public Page<PersonProfileDto> getPersonProfiles(Pageable pageable) {

        LOG.debug("Retrieving person profiles");

        Objects.requireNonNull(pageable);

        //TODO - add exception handling
        Page<PersonProfile> personList = personProfileRepository.findAll(pageable);
        long total = personProfileRepository.count();

        List<PersonProfileDto> personProfileDtoSet = new LinkedList<>();
        for (PersonProfile personProfile : personList) {
            personProfileDtoSet.add(personProfileConverter.convert(personProfile));
        }

        LOG.debug("Done retrieving person profiles");

        return new PageImpl<>(personProfileDtoSet, pageable, total);
    }

    /**
     * Generates and returns a single mock person
     *
     * @param personId
     * @return the mock person
     */
    @Override
    public PersonProfileDto getPersonProfileByUid(UUID personId) {

        LOG.debug("Retrieving person profile: {}", personId);

        Objects.requireNonNull(personId);

        //TODO - add exception handling
        PersonProfile personProfile = personProfileRepository.findByUid(personId);
        if (personProfile == null) {
            throw new EntityNotFoundException("Could not find person profile with given uid: " + personId);
        }

        LOG.debug("Done retrieving person profile: {}", personId);

        return personProfileConverter.convert(personProfile);
    }

    @Override
    public PersonProfileDto addPersonProfile(PersonProfileDto personProfileDto) {
        LOG.debug("Adding person profile: {}", personProfileDto);

        Objects.requireNonNull(personProfileDto);

        //TODO - add exception handling
        PersonProfile personProfile = personProfileConverter.convert(personProfileDto);
        personProfile.setUid(DataUtils.generateUUID());
        personProfile = personProfileRepository.save(personProfile);

        return personProfileConverter.convert(personProfile);
    }

    @Override
    public PersonProfileDto updatePersonProfile(PersonProfileDto personProfileDto) {
        LOG.debug("Updating person profile: {}", personProfileDto);

        Objects.requireNonNull(personProfileDto);

        //TODO - handle exceptions
        PersonProfile oldPersonProfile = personProfileRepository.findByUid(personProfileDto.getId());
        if (oldPersonProfile == null) {
            throw new EntityNotFoundException("Could not find person profile with given uid: " + personProfileDto.getId());
        }

        //TODO - do some input filtering
        PersonProfile newPersonProfile = personProfileConverter.convert(personProfileDto);
        newPersonProfile.setId(oldPersonProfile.getId());
        newPersonProfile = personProfileRepository.save(newPersonProfile);

        return personProfileConverter.convert(newPersonProfile);

    }

    @Override
    public PersonProfileDto deletePersonProfile(UUID personProfileId) {
        LOG.debug("Updating person profile: {}", personProfileId);

        Objects.requireNonNull(personProfileId);

        PersonProfile personProfile = personProfileRepository.findByUid(personProfileId);
        if (personProfile == null) {
            throw new EntityNotFoundException("Could not find person profile with given uid: " + personProfileId);
        }

        personProfileRepository.delete(personProfile);
        LOG.debug("Done deleting person profile");

        return personProfileConverter.convert(personProfile);
    }

    public Set<PersonSkillDto> getPersonProfileSkillsByUid(UUID personProfileId) {
        LOG.debug("Retrieving skills for person {}", personProfileId);

        PersonProfile personProfile = personProfileRepository.findByUid(personProfileId);
        Objects.requireNonNull(personProfile);

        Set<PersonSkill> personSkills = personProfile.getPersonSkills();
        Set<PersonSkillDto> personSkillDtos = personSkills.stream()
                .map(personSkill -> personSkillConverter.convert(personSkill)).collect(Collectors.toSet());

        return personSkillDtos;
    }

    public PersonSkillDto deletePersonSkill(UUID personSkillId) {
        LOG.debug("Deleting person skill {}", personSkillId);

        PersonSkill personSkill = personSkillRepository.findByUid(personSkillId);
        personSkillRepository.delete(personSkill);

        PersonSkillDto result = personSkillConverter.convert(personSkill);

        LOG.debug("Done deleting person skill {}", personSkillId);

        return result;
    }

    @Override
    public PersonSkillDto addPersonSkill(PersonSkillDto personSkillDto) {

        LOG.debug("Add person profile skill {}", personSkillDto);

        //TODO - some value filtering for the scoring, just in case

        PersonProfile personProfile = personProfileRepository.findByUid(personSkillDto.getPersonProfileId());
        Objects.requireNonNull(personProfile);

        Skill skill = skillRepository.findByUid(personSkillDto.getSkillId());
        Objects.requireNonNull(skill);

        PersonSkill personProfileSkill = personSkillConverter.convert(personSkillDto);
        personProfileSkill.setUid(DataUtils.generateUUID());
        personProfileSkill = personSkillRepository.save(personProfileSkill);

        PersonSkillDto result = personSkillConverter.convert(personProfileSkill);

        LOG.debug("Done adding person profile skill {}", personSkillDto);

        return result;

    }
}
