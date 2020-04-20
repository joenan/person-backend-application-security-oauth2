# person-backend-application-security-oauth2
SpringBoot Rest Security Oauth2 Authentication with JWT authorization

##Note: 
The application.properties has a default profile configuration (spring.profiles.active=dev). <br/>This implies that the application-dev.properties file is the selected profile. you can however change it to prod (spring.profiles.active=prod). By doing so, the aplication-prod.properties file will be the default configuration.

## Demo
The Instruction on Installing and Running this application can be found at https://www.youtube.com/watch?v=ND5bSriaOE8&feature=youtu.be
Watch the video and you will learn how to create user credentials, client credentials, get access token, verify access token and use the access token to send get and post request via Postman.

## Build
To Build this application on the command Line, Run the command "mvn install"

## Run
To run this application on the command Line, Run the command "mvn spring-boot:run". The backend can be accessed via this context path http://localhost:8080 after  it has run.

## Deploy
To deploy this application in Tomcat Apache Server, copy the built application to tomcat/webapps directory. The app will be automatically deployed.

