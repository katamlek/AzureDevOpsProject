package com.katamlek.training.skillomatic.person.api.v1.controllers;

import com.katamlek.training.skillomatic.person.service.PersonService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile(TestHelper.PROFILE_API_CONTROLLER)
@Configuration
public class TestConfiguration {

    @Primary
    @Bean
    public PersonService personService() {
        return Mockito.mock(PersonService.class);
    }

}
