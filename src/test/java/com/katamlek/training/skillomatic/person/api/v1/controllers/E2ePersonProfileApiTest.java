package com.katamlek.training.skillomatic.person.api.v1.controllers;

import com.katamlek.training.skillomatic.person.dto.PersonProfileDto;
import com.katamlek.training.skillomatic.person.service.PersonService;
import com.katamlek.training.skillomatic.test.helper.TestProfiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles({TestProfiles.PROFILE_TEST, TestHelper.PROFILE_API_E2E_TEST})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:test-embedded-db.properties")
public class E2ePersonProfileApiTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PersonService personService;

    @Test
    public void test_PersonsController_addPerson() {

        PersonProfileDto personProfileDto = new PersonProfileDto();
        personProfileDto.setFirstName("Test");
        personProfileDto.setLastName("person");
        personProfileDto.setEmail("test.person@mailing-service.com");

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
        personProfileDto.setId(response.getBody().getId());

        assertEquals(personProfileDto, response.getBody());
    }

}
