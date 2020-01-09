package com.katamlek.training.skillomatic.skill.persistence.repository;

import com.katamlek.training.skillomatic.skill.persistence.model.PersonSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonSkillRepository extends JpaRepository<PersonSkill, Long> {

    PersonSkill findByUid(UUID uuid);

}
