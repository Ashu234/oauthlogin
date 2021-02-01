# oauthlogin
Demo Oauth Login with User Sessions

Oauth Login and UserSession Example Project with Spring Boot


Build and Deploy the Project
mvn clean install
This is a Spring Boot project, so you can deploy it by simply using the main class: OauthloginApplication.java

Once deployed, you can access the app at:

https://localhost:8080

Set up **H2 Database**
By default, the project is configured to use the embedded H2 database. 
After running the application paste http://localhost:8080/h2-console/. on your browser.

Visit Swagger to read API documentation:
After runninng the application visit http://localhost:8080/swagger-ui.html.

** You need to first test  login Login before testing User Session API to get some results ***

Starting the Project:

1. Intellij Idea or any IDE.
   
   1.1 Open the project. Select pom.xml file and hit open.
   
   1.2 Find com/oauth/login/OauthloginApplication.java and run the file as java main class.
   
2. Or From Terminal.
   
   2.1 You need maven installed on your system. To check open terminal and type "mvn -version". If recognized then you are good else install maven.
   
   2.2 Download the project eg: C/user/username/Downloads/projectname.
   
   2.3 cd to projectname directory where pom.xml is present.
   
   2.4 run mvn install - This will package the spring project in executable jar file which will be present inside /targets folder filename-SNAPSHOT.
   
   2.5 once compiled run "mvn spring-boot:run" command to start the application. Application starts at localhost:8080.
   
3. To Test Application:
   
   3.1 Open Postman
   
         3.1.1 To test login API paste url http://localhost:8080/login/ and select "POST" from drop down
   
         3.1.2 Select Body radio button -> raw -> JSON
   
         3.1.3 Paste { "email": "edwin@cerebri.com", "password": "jbcv42016@@%$JGT09"}
   
         3.1.4 Hit enter: should get HttpStatus 200 and response { "authToken": "ab67b1a0-7105-4a02-b6dc-8cb8080e2b34" } token will vary every time.
   
         3.1.5 Open new request page in postman and paste http://localhost:8080/date/range/ and select "POST" from drop down to test User Sessions
   
         3.1.6 Repeat step 3.1.2 and paste { "fromDate" : "2021-01-31", "toDate" : "2021-02-01"}
   
         3.1.7 Hit send : should get HttpStatus 200 and response 
            [ {"userId": "e399cca1-80c8-4d14-b43a-15ede0cda673", "authToken": "789029e1-c13c-48ab-801e-25c7834c9eb1","createdAt": "2021-02-01T03:53Z"}, {"userId": "e399cca1-80c8-4d14-b43a-15ede0cda673",   "authToken": "ab67b1a0-7105-4a02-b6dc-8cb8080e2b34",
        "createdAt": "2021-02-01T03:53Z"}]
   
   3.2 Using Terminal
   
         3.2.1 open terminal and paste curl  -H "Content-Type: application/json"  -d '{"email":"edwin@cerebri.com", "password":"jbcv42016@@%$JGT09"}'  http://localhost:8080/login/

         3.2.2 To Test User Sessions paste - curl  -H "Content-Type: application/json"  -d '{"fromDate":"2021-01-31", "toDate":"2021-02-01"}'  http://localhost:8080/date/range/
       
       
       

