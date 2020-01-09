package com.katamlek.training.skillomatic.skill.service.converter;

import com.katamlek.training.skillomatic.helper.EntityDtoConverter;
import com.katamlek.training.skillomatic.person.dto.PersonSkillDto;
import com.katamlek.training.skillomatic.person.persistence.models.PersonProfile;
import com.katamlek.training.skillomatic.person.persistence.repository.PersonProfileRepository;
import com.katamlek.training.skillomatic.skill.persistence.model.PersonSkill;
import com.katamlek.training.skillomatic.skill.persistence.model.Skill;
import com.katamlek.training.skillomatic.skill.persistence.repository.PersonSkillRepository;
import com.katamlek.training.skillomatic.skill.persistence.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonSkillConverter implements EntityDtoConverter<PersonSkill, PersonSkillDto> {

    @Autowired
    private PersonSkillRepository personSkillRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private PersonProfileRepository personProfileRepository;

    @Override
    public PersonSkillDto convert(PersonSkill entity) {

        PersonSkillDto personSkillDto = new PersonSkillDto();
        personSkillDto.setId(entity.getUid());
        personSkillDto.setSkillName(entity.getSkill().getSkillName());
        personSkillDto.setSkillScore(entity.getSkillScore());
        personSkillDto.setSkillId(entity.getSkill().getUid());
        personSkillDto.setPersonProfileId(entity.getPersonProfile().getUid());

        return personSkillDto;
    }

    @Override
    public PersonSkill convert(PersonSkillDto personSkillDto) {

        PersonSkill personSkill = new PersonSkill();

        boolean fetchNewSkill = true;
        boolean fetchNewPersonProfile = true;

        if (personSkillDto.getId() != null){
            personSkill = personSkillRepository.findByUid(personSkillDto.getId());

            if (personSkillDto.getSkillId().equals(personSkill.getSkill().getUid())){
               fetchNewSkill = false;
            }

            if (personSkillDto.getPersonProfileId().equals(personSkill.getPersonProfile().getUid())){
                fetchNewPersonProfile = false;
            }
        }

        if (fetchNewSkill){
            Skill skill = skillRepository.findByUid(personSkillDto.getSkillId());
            personSkill.setSkill(skill);
        }

        if (fetchNewPersonProfile){
            PersonProfile personProfile = personProfileRepository.findByUid(personSkillDto.getPersonProfileId());
            personSkill.setPersonProfile(personProfile);
        }

        personSkill.setSkillScore(personSkillDto.getSkillScore());

        return personSkill;
    }

}
