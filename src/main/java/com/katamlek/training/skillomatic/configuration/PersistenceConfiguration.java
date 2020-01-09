package com.katamlek.training.skillomatic.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EntityScan("com.katamlek.training.skillomatic.person.persistence")
public class PersistenceConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(PersistenceConfiguration.class);

    @Value("${skillomatic.datasource.db.name}")
    private String dbName;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;


    @PostConstruct
    public void init(){
        LOGGER.info("Configured persistence with: {}" , this);
    }


    public String getDbName() {
        return dbName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }


    @Override
    public String toString() {
        return "\n\nPersistenceConfiguration{" +
                "\n\tdbName='" + dbName + '\'' +
                ", \n\tdbUrl='" + dbUrl + '\'' +
                ", \n\tdbUser='" + dbUser + '\'' +
                "}\n";
    }

}
