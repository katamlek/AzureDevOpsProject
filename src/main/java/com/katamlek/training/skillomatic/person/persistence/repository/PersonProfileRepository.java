package com.katamlek.training.skillomatic.person.persistence.repository;

import com.katamlek.training.skillomatic.person.persistence.models.PersonProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonProfileRepository extends JpaRepository<PersonProfile, Long> {

    PersonProfile findByUid(UUID personId);

}
