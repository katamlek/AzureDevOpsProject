package com.katamlek.training.skillomatic.skill.controllers;

import com.katamlek.training.skillomatic.skill.dto.SkillDto;
import com.katamlek.training.skillomatic.skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;
import java.util.UUID;

@RestController
public class SkillController {

    public static final String SKILL_ID = "skillId";

    public static final String ENDPOINT_SKILLS_BASE = "/api/v1/skills";
    public static final String ENDPOINT_SKILLS_GET_LIST = ENDPOINT_SKILLS_BASE;
    public static final String ENDPOINT_SKILLS_GET_ONE = ENDPOINT_SKILLS_BASE + "/{" + SKILL_ID + "}";
    public static final String ENDPOINT_SKILLS_ADD = ENDPOINT_SKILLS_BASE;
    public static final String ENDPOINT_SKILLS_UPDATE = ENDPOINT_SKILLS_GET_ONE;
    public static final String ENDPOINT_SKILLS_DELETE = ENDPOINT_SKILLS_GET_ONE;

    @Autowired
    private SkillService skillService;

    @GetMapping(value = ENDPOINT_SKILLS_BASE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<SkillDto>> getSkills(@NotNull Pageable pageable) {

        Page<SkillDto> skillDtos = skillService.getSkills(pageable);

        return new ResponseEntity<>(skillDtos, HttpStatus.OK);
    }


    @GetMapping(value = ENDPOINT_SKILLS_GET_ONE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SkillDto> getSkillDto(@PathVariable(SKILL_ID) UUID skillId) {
        SkillDto skillDto = skillService.getSkillByUid(skillId);
        return new ResponseEntity<>(skillDto, HttpStatus.OK);
    }


    @PostMapping(value = ENDPOINT_SKILLS_ADD,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SkillDto> addSkill(@RequestBody SkillDto skillDto) {
        skillDto = skillService.addSkill(skillDto);
        return new ResponseEntity<>(skillDto, HttpStatus.OK);
    }


    @PutMapping(value = ENDPOINT_SKILLS_UPDATE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SkillDto> updateSkillDto(
            @PathVariable(SKILL_ID) UUID skillId,
            @RequestBody SkillDto skillDto) {
        if (!skillDto.getId().equals(skillId)) {
            throw new InvalidParameterException("Provided skill and skillId do not match");
        }
        skillDto = skillService.updateSkill(skillDto);

        return new ResponseEntity<>(skillDto, HttpStatus.OK);
    }


    @DeleteMapping(value = ENDPOINT_SKILLS_DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SkillDto> deleteSkillDto(
            @PathVariable(SKILL_ID) UUID skillId) {
        SkillDto result = skillService.deleteSkill(skillId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
