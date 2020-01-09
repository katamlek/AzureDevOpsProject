SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

INSERT INTO `${skillomaticdb}`.`skill`
    (`uid`, `skill_name`)
VALUES
    (UNHEX(REPLACE(UUID(),'-','')), 'Windows'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Linux'),
    (UNHEX(REPLACE(UUID(),'-','')), 'MySQL'),
    (UNHEX(REPLACE(UUID(),'-','')), 'PostgreSQL'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Cassandra'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Nginx'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Java'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Python'),
    (UNHEX(REPLACE(UUID(),'-','')), 'PHP'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Bash'),
    (UNHEX(REPLACE(UUID(),'-','')), 'CSS'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Oracle Virtualbox'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Docker'),
    (UNHEX(REPLACE(UUID(),'-','')), 'IntelliJ IDEA'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Maven'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Jenkins'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Git'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Trello'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Jira'),
    (UNHEX(REPLACE(UUID(),'-','')), 'BitBucket'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Confluence'),
    (UNHEX(REPLACE(UUID(),'-','')), 'JFrog Artifactory'),
    (UNHEX(REPLACE(UUID(),'-','')), 'Java Spring Framework');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
