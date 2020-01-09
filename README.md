# Skill'o'Matic

## Prerequisites
1. Java 8 or 11 
2. Maven 
3. IntelliJ IDEA 

## Build your application with Maven
1. Navigate to the project directory
2. In a terminal: `mvn clean package`
3. Check your **target** directory; you should find a file called `skillomatic-1.0-SNAPSHOT.jar`

## Profiles
There are 3 profiles with which the application can be started.
1. `dev` - developer profile with no authentication required
2. `dev-security` - developer profile with oAuth authentication required
3. `production` - production profile with oAuth authentication required

## Run your application
1. Navigate to project directory
2. In a terminal: `java -jar target/skillomatic-1.0-SNAPSHOT.jar`
3. Enjoy the app ;)

## Security
This application can be setup to run with an oAuth authentication provider. 
Please consider the following when starting the application.
Use the env `SPRING_ACTIVE_PROFILES` to startup the app. Default is `dev`;

Note that the app has an embedded server running, which binds to port 8080 by default.

