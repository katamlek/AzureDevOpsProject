package com.katamlek.training.skillomatic.person.api.v1.controllers;

import com.katamlek.training.skillomatic.person.dto.PersonProfileDto;
import com.katamlek.training.skillomatic.person.service.PersonService;
import com.katamlek.training.skillomatic.test.helper.RestResponsePage;
import com.katamlek.training.skillomatic.test.helper.TestProfiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles({TestProfiles.PROFILE_TEST, TestHelper.PROFILE_API_CONTROLLER})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:test-embedded-db.properties")
public class PersonProfileApiControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PersonService personService;

    @Test
    public void test_PersonsController_getPersons_paged() {

        int nrOfEntries = 5;

        Pageable pageable = PageRequest.of(0, nrOfEntries);
        Page<PersonProfileDto> pagedPersons = TestHelper.createPersons(pageable);
        when(personService.getPersonProfiles(pageable)).thenReturn(pagedPersons);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(PersonProfileController.ENDPOINT_PERSON_PROFILES_GET_LIST)
                .queryParam("page", 0)
                .queryParam("size", nrOfEntries);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<RestResponsePage<PersonProfileDto>> response =
                testRestTemplate.exchange(
                        uriBuilder.toUriString(),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<RestResponsePage<PersonProfileDto>>() {
                        });


        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertEquals(response.getBody().getContent().size(), nrOfEntries);

        assertEquals(pagedPersons.getContent(), response.getBody().getContent());

    }


    @Test
    public void test_PersonsController_getPersons() {

        UUID personId = UUID.randomUUID();

        PersonProfileDto personProfileDto = new PersonProfileDto();
        personProfileDto.setId(personId);
        personProfileDto.setFirstName("Test");
        personProfileDto.setLastName("person");
        personProfileDto.setEmail("test.person@mailing-service.com");

        when(personService.getPersonProfileByUid(personId)).thenReturn(personProfileDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(PersonProfileController.ENDPOINT_PERSON_PROFILES_GET_ONE);
        String uri = uriBuilder.buildAndExpand(personId).toUriString();

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<PersonProfileDto> response =
                testRestTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        entity,
                        PersonProfileDto.class);


        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response.getBody());

        assertEquals(personProfileDto, response.getBody());
    }


    @Test
    public void test_PersonsController_addPerson() {

        UUID personId = UUID.randomUUID();

        PersonProfileDto personProfileDto = new PersonProfileDto();
        personProfileDto.setId(personId);
        personProfileDto.setFirstName("Test");
        personProfileDto.setLastName("person");
        personProfileDto.setEmail("test.person@mailing-service.com");

        when(personService.addPersonProfile(personProfileDto)).thenReturn(personProfileDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(personProfileDto, headers);

        ResponseEntity<PersonProfileDto> response =
                testRestTemplate.exchange(
                        PersonProfileController.ENDPOINT_PERSON_PROFILES_ADD,
                        HttpMethod.POST,
                        entity,
                        PersonProfileDto.class);


        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response.getBody());

        assertEquals(personProfileDto, response.getBody());
    }


    @Test
    public void test_PersonsController_updatePerson() {

        UUID personId = UUID.randomUUID();

        PersonProfileDto personProfileDto = new PersonProfileDto();
        personProfileDto.setId(personId);
        personProfileDto.setFirstName("Test");
        personProfileDto.setLastName("person");
        personProfileDto.setEmail("test.person@mailing-service.com");

        when(personService.updatePersonProfile(personProfileDto)).thenReturn(personProfileDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(PersonProfileController.ENDPOINT_PERSON_PROFILES_DELETE);
        String uri = uriBuilder.buildAndExpand(personId).toUriString();

        HttpEntity<?> entity = new HttpEntity<>(personProfileDto, headers);

        ResponseEntity<PersonProfileDto> response =
                testRestTemplate.exchange(
                        uri,
                        HttpMethod.PUT,
                        entity,
                        PersonProfileDto.class);


        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response.getBody());

        assertEquals(personProfileDto, response.getBody());
    }


    @Test
    public void test_PersonsController_deletePerson() {

        UUID personId = UUID.randomUUID();

        PersonProfileDto personProfileDto = new PersonProfileDto();
        personProfileDto.setId(personId);
        personProfileDto.setFirstName("Test");
        personProfileDto.setLastName("person");
        personProfileDto.setEmail("test.person@mailing-service.com");

        when(personService.deletePersonProfile(personId)).thenReturn(personProfileDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(PersonProfileController.ENDPOINT_PERSON_PROFILES_DELETE);
        String uri = uriBuilder.buildAndExpand(personId).toUriString();

        HttpEntity<?> entity = new HttpEntity<>(personProfileDto, headers);

        ResponseEntity<PersonProfileDto> response =
                testRestTemplate.exchange(
                        uri,
                        HttpMethod.DELETE,
                        entity,
                        PersonProfileDto.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
