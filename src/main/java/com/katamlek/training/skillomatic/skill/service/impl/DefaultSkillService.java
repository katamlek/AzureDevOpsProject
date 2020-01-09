package com.katamlek.training.skillomatic.skill.service.impl;

import com.katamlek.training.skillomatic.helper.DataUtils;
import com.katamlek.training.skillomatic.skill.dto.SkillDto;
import com.katamlek.training.skillomatic.skill.persistence.model.Skill;
import com.katamlek.training.skillomatic.skill.persistence.repository.SkillRepository;
import com.katamlek.training.skillomatic.skill.service.SkillService;
import com.katamlek.training.skillomatic.skill.service.converter.SkillConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
public class DefaultSkillService implements SkillService {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultSkillService.class);

    @Autowired
    private final SkillConverter skillConverter;

    @Autowired
    private SkillRepository skillRepository;

    public DefaultSkillService(
            SkillRepository skillRepository, SkillConverter skillConverter) {
        this.skillRepository = skillRepository;
        this.skillConverter = skillConverter;
    }

    @Override
    public Page<SkillDto> getSkills(Pageable pageable) {

        LOG.debug("Retrieving skills");

        Objects.requireNonNull(pageable);

        Page<Skill> skills = skillRepository.findAll(pageable);
        long total = skillRepository.count();

        List<SkillDto> skillDtoSet = new LinkedList<>();
        for (Skill skill : skills) {
            skillDtoSet.add(skillConverter.convert(skill));
        }

        LOG.debug("Done retrieving skills");

        return new PageImpl<>(skillDtoSet, pageable, total);
    }

    @Override
    public SkillDto getSkillByUid(UUID skillId) {

        LOG.debug("Retrieving skill: {}", skillId);

        Objects.requireNonNull(skillId);

        Skill skill = skillRepository.findByUid(skillId);
        if (skill == null) {
            throw new EntityNotFoundException("Could not find skill with given uid: " + skillId);
        }

        LOG.debug("Done retrieving skill: {}", skillId);

        return skillConverter.convert(skill);
    }

    @Override
    public SkillDto addSkill(SkillDto skillDto) {
        LOG.debug("Adding skill: {}", skillDto);

        Objects.requireNonNull(skillDto);

        Skill skill = skillConverter.convert(skillDto);
        skill.setUid(DataUtils.generateUUID());
        skill = skillRepository.save(skill);

        return skillConverter.convert(skill);
    }

    @Override
    public SkillDto updateSkill(SkillDto skillDto) {
        LOG.debug("Updating skill: {}", skillDto);

        Objects.requireNonNull(skillDto);

        Skill oldSkill = skillRepository.findByUid(skillDto.getId());
        if (oldSkill == null) {
            throw new EntityNotFoundException("Could not find skill with given uid: " + skillDto.getId());
        }

        Skill newSkill = skillConverter.convert(skillDto);
        newSkill.setId(oldSkill.getId());
        newSkill = skillRepository.save(newSkill);

        return skillConverter.convert(newSkill);
    }

    @Override
    public SkillDto deleteSkill(UUID skillId) {
        LOG.debug("Deleting skill: {}", skillId);

        Skill skill = skillRepository.findByUid(skillId);

        // check if the skill is bound to a consultant
        if (skill.getPersonSkills().isEmpty()) {
            skillRepository.delete(skill);
            LOG.debug("Done deleting skill");
            return skillConverter.convert(skill);
        } else return new SkillDto();

    }
}
