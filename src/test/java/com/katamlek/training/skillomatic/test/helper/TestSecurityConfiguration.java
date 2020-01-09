package com.katamlek.training.skillomatic.test.helper;

import com.katamlek.training.skillomatic.configuration.DevSecurityConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Profile(TestProfiles.PROFILE_TEST)
@EnableWebSecurity
public class TestSecurityConfiguration extends DevSecurityConfiguration {

}
