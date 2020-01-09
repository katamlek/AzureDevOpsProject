package com.katamlek.training.skillomatic.person.api.v1.controllers;

import com.katamlek.training.skillomatic.person.dto.PersonProfileDto;
import com.katamlek.training.skillomatic.person.dto.PersonSkillDto;
import com.katamlek.training.skillomatic.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;
import java.util.Set;
import java.util.UUID;


/**
 * <p>PersonProfileController class exposing basic CRUD (GET, POST, PUT, DELETE)
 * HTTP REST API functionality for PersonProfiles
 */
@RestController
public class PersonProfileController {

    public static final String PERSON_PROFILE_ID = "personProfileId";
    public static final String PERSON_SKILL_ID = "personSkillId";

    public static final String ENDPOINT_PERSON_PROFILES_BASE = "/api/v1/personProfiles";
    public static final String ENDPOINT_PERSON_PROFILES_GET_LIST = ENDPOINT_PERSON_PROFILES_BASE;
    public static final String ENDPOINT_PERSON_PROFILES_GET_ONE = ENDPOINT_PERSON_PROFILES_BASE + "/{" + PERSON_PROFILE_ID + "}";
    public static final String ENDPOINT_PERSON_PROFILES_ADD = ENDPOINT_PERSON_PROFILES_BASE;
    public static final String ENDPOINT_PERSON_PROFILES_UPDATE = ENDPOINT_PERSON_PROFILES_GET_ONE;
    public static final String ENDPOINT_PERSON_PROFILES_DELETE = ENDPOINT_PERSON_PROFILES_GET_ONE;
    public static final String ENDPOINT_PERSON_PROFILES_GET_ONE_GET_SKILLS = ENDPOINT_PERSON_PROFILES_BASE + "/{" + PERSON_PROFILE_ID + "}" + "/skills";
    public static final String ENDPOINT_DELETE_PERSON_SKILL = ENDPOINT_PERSON_PROFILES_GET_ONE + "/skills/{" + PERSON_SKILL_ID + "}";
    public static final String ENDPOINT_PERSON_PROFILE_SKILL_ADD = ENDPOINT_PERSON_PROFILES_GET_ONE + "/skills";

    /**
     * Underlying person service which provides actual functionality related to endpoint behavior
     */
    @Autowired
    private PersonService personService;

    /**
     * GET implementation for /api/v1/personProfiles endpoint.
     * Accepts a {@link Pageable} object as input which can be populated via GET params.
     * <pre>Example: http(s)://domain/api/v1/personProfiles?page=0&size=15`
     *
     * @param pageable - paged request object populated by spring from GET params
     * @return the paged person list with pagination information for the implementing client
     */
    @GetMapping(value = ENDPOINT_PERSON_PROFILES_BASE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<PersonProfileDto>> getPersonProfiles(@NotNull Pageable pageable) {

        Page<PersonProfileDto> persons = personService.getPersonProfiles(pageable);

        return new ResponseEntity<>(persons, HttpStatus.OK);
    }


    @GetMapping(value = ENDPOINT_PERSON_PROFILES_GET_ONE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonProfileDto> getPersonProfile(@PathVariable(PERSON_PROFILE_ID) UUID personProfileId) {

        //TODO - filter the input

        PersonProfileDto person = personService.getPersonProfileByUid(personProfileId);
        return new ResponseEntity<>(person, HttpStatus.OK);

    }

    @PostMapping(value = ENDPOINT_PERSON_PROFILES_ADD,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonProfileDto> addPersonProfile(@RequestBody PersonProfileDto personProfileDto) {

        //TODO - filter the input

        personProfileDto = personService.addPersonProfile(personProfileDto);
        return new ResponseEntity<>(personProfileDto, HttpStatus.OK);
    }

    @PutMapping(value = ENDPOINT_PERSON_PROFILES_UPDATE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonProfileDto> updatePersonProfile(
            @PathVariable(PERSON_PROFILE_ID) UUID personId,
            @RequestBody PersonProfileDto personProfileDto) {

        //TODO - filter the input
        if (!personProfileDto.getId().equals(personId)) {
            throw new InvalidParameterException("Provided person and personId do not match");
        }
        personProfileDto = personService.updatePersonProfile(personProfileDto);

        return new ResponseEntity<>(personProfileDto, HttpStatus.OK);
    }

    @DeleteMapping(value = ENDPOINT_PERSON_PROFILES_DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonProfileDto> deletePersonProfile(
            @PathVariable(PERSON_PROFILE_ID) UUID personProfileId) {

        //TODO - filter the input

        PersonProfileDto result = personService.deletePersonProfile(personProfileId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = ENDPOINT_PERSON_PROFILES_GET_ONE_GET_SKILLS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Set<PersonSkillDto>> getPersonProfileSkills(@PathVariable(PERSON_PROFILE_ID) UUID personProfileId) {

        Set<PersonSkillDto> personProfiles = personService.getPersonProfileSkillsByUid(personProfileId);
        return new ResponseEntity<>(personProfiles, HttpStatus.OK);
    }

    @DeleteMapping(value = ENDPOINT_DELETE_PERSON_SKILL,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonSkillDto> deletePersonSkill(
            @PathVariable(PERSON_PROFILE_ID) UUID personProfileId,
            @PathVariable(PERSON_SKILL_ID) UUID personSkillId) {

        //TODO - filter the input

        PersonSkillDto result = personService.deletePersonSkill(personSkillId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = ENDPOINT_PERSON_PROFILE_SKILL_ADD,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonSkillDto> addPersonSkill(
            @PathVariable(PERSON_PROFILE_ID) UUID personProfileId,
            @RequestBody PersonSkillDto personSkillDto
    ) {
        //TODO - filter the input

        if (!personProfileId.equals(personSkillDto.getPersonProfileId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PersonSkillDto result = personService.addPersonSkill(personSkillDto);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
