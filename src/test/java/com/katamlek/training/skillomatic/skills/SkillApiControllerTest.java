package com.katamlek.training.skillomatic.skills;

import com.katamlek.training.skillomatic.skill.controllers.SkillController;
import com.katamlek.training.skillomatic.skill.dto.SkillDto;
import com.katamlek.training.skillomatic.skill.service.SkillService;
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

import static com.katamlek.training.skillomatic.skills.SkillTestHelper.SKILL_API_CONTROLLER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles({TestProfiles.PROFILE_TEST, SKILL_API_CONTROLLER})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:test-embedded-db.properties")
public class SkillApiControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private SkillService skillService;

    @Test
    public void test_SkillController_getSkills_paged() {

        int nrOfEntries = 5;

        Pageable pageable = PageRequest.of(0, nrOfEntries);
        Page<SkillDto> pagedSkills = SkillTestHelper.createSkills(pageable);
        when(skillService.getSkills(pageable)).thenReturn(pagedSkills);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(SkillController.ENDPOINT_SKILLS_GET_LIST)
                .queryParam("page", 0)
                .queryParam("size", nrOfEntries);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<RestResponsePage<SkillDto>> response =
                testRestTemplate.exchange(
                        uriBuilder.toUriString(),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<RestResponsePage<SkillDto>>() {
                        });


        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertEquals(response.getBody().getContent().size(), nrOfEntries);

        assertEquals(pagedSkills.getContent(), response.getBody().getContent());

    }


    @Test
    public void test_SkillController_getSkills() {

        UUID skillId = UUID.randomUUID();

        SkillDto skillDto = new SkillDto();
        skillDto.setId(skillId);
        skillDto.setSkillName("Test");

        when(skillService.getSkillByUid(skillId)).thenReturn(skillDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(SkillController.ENDPOINT_SKILLS_GET_ONE);
        String uri = uriBuilder.buildAndExpand(skillId).toUriString();

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<SkillDto> response =
                testRestTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        entity,
                        SkillDto.class);


        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response.getBody());

        assertEquals(skillDto, response.getBody());
    }


    @Test
    public void test_SkillController_addSkill() {

        UUID skillId = UUID.randomUUID();

        SkillDto skillDto = new SkillDto();
        skillDto.setId(skillId);
        skillDto.setSkillName("Test");

        when(skillService.addSkill(skillDto)).thenReturn(skillDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(skillDto, headers);

        ResponseEntity<SkillDto> response =
                testRestTemplate.exchange(
                        SkillController.ENDPOINT_SKILLS_ADD,
                        HttpMethod.POST,
                        entity,
                        SkillDto.class);


        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response.getBody());

        assertEquals(skillDto, response.getBody());
    }


    @Test
    public void test_SkillController_updateSkill() {

        UUID skillId = UUID.randomUUID();

        SkillDto skillDto = new SkillDto();
        skillDto.setId(skillId);
        skillDto.setSkillName("Test");

        when(skillService.updateSkill(skillDto)).thenReturn(skillDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(SkillController.ENDPOINT_SKILLS_DELETE);
        String uri = uriBuilder.buildAndExpand(skillId).toUriString();

        HttpEntity<?> entity = new HttpEntity<>(skillDto, headers);

        ResponseEntity<SkillDto> response =
                testRestTemplate.exchange(
                        uri,
                        HttpMethod.PUT,
                        entity,
                        SkillDto.class);


        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response.getBody());

        assertEquals(skillDto, response.getBody());
    }


    @Test
    public void test_SkillController_deleteSkill() {

        UUID skillId = UUID.randomUUID();

        SkillDto skillDto = new SkillDto();
        skillDto.setId(skillId);
        skillDto.setSkillName("Test");

        when(skillService.deleteSkill(skillId)).thenReturn(skillDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(SkillController.ENDPOINT_SKILLS_DELETE);
        String uri = uriBuilder.buildAndExpand(skillId).toUriString();

        HttpEntity<?> entity = new HttpEntity<>(skillDto, headers);

        ResponseEntity<SkillDto> response =
                testRestTemplate.exchange(
                        uri,
                        HttpMethod.DELETE,
                        entity,
                        SkillDto.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
