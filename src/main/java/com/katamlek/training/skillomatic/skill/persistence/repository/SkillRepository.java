package com.katamlek.training.skillomatic.skill.persistence.repository;

import com.katamlek.training.skillomatic.skill.persistence.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findByUid(UUID skillId);

}
