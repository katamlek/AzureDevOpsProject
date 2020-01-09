package com.katamlek.training.skillomatic.person.service.converter;

import com.katamlek.training.skillomatic.helper.EntityDtoConverter;
import com.katamlek.training.skillomatic.person.dto.PersonProfileDto;
import com.katamlek.training.skillomatic.person.persistence.models.PersonProfile;
import org.springframework.stereotype.Component;

@Component
public class PersonProfileConverter implements
        EntityDtoConverter<PersonProfile, PersonProfileDto> {

    @Override
    public PersonProfileDto convert(PersonProfile personProfile) {
        PersonProfileDto personProfileDto = new PersonProfileDto();

        personProfileDto.setId(personProfile.getUid());
        personProfileDto.setFirstName(personProfile.getFirstName());
        personProfileDto.setLastName(personProfile.getLastName());
        personProfileDto.setEmail(personProfile.getEmailAddress());
        return personProfileDto;
    }

    @Override
    public PersonProfile convert(PersonProfileDto personProfileDto) {

        PersonProfile personProfile = new PersonProfile();
        personProfile.setUid(personProfileDto.getId());
        personProfile.setEmailAddress(personProfileDto.getEmail());
        personProfile.setFirstName(personProfileDto.getFirstName());
        personProfile.setLastName(personProfileDto.getLastName());

        return personProfile;

    }

}
