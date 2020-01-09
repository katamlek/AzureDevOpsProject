package com.katamlek.training.skillomatic.skills;

import com.katamlek.training.skillomatic.skill.controllers.SkillController;
import com.katamlek.training.skillomatic.skill.dto.SkillDto;
import com.katamlek.training.skillomatic.skill.service.SkillService;
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

import static com.katamlek.training.skillomatic.skills.SkillTestHelper.SKILL_API_E2E_TEST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles({TestProfiles.PROFILE_TEST, SKILL_API_E2E_TEST})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:test-embedded-db.properties")
public class E2eSkillApiTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private SkillService skillService;

    @Test
    public void test_SkillController_addSkill() {


        SkillDto skillDto = new SkillDto();
        skillDto.setSkillName("Test");

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

        assertEquals(skillDto.getSkillName(), response.getBody().getSkillName());
    }

}
