## Introduction to the project
The repository contains application for users to login using email and password or using OAuth2 (Google or GitHub).
The backend app is meant to be run together with the frontend Angular application. You can find the corresponding repository [here](https://github.com/anitalakhadze/multiple-auth-ui).

## Technologies used
- **Backend**
    - Application is built using Java 17, Spring Boot 3 and Spring Security 6. Dependencies are managed using Maven. Authentication is done using JWT.
- **Database**
    - Postgresql is used as the database.


## Steps to run the applications
1. **Start up the database:**
    - Run Postgresql docker container with the following command (replace _{PASSWORD}_ with your own password):

      `docker run --name multiple-auth-app-postgres -e POSTGRES_PASSWORD={PASSWORD} -d -p 127.0.0.1:5432:5432 postgres`

      Don't change the port configuration when running in a local environment. The syntax of the mapping is for extra security, so that the database is not accessible from outside the container.

      If you change the password to the postgresql database, make sure to update the password in the application.properties file of the **_user-app-api_** project.
    - If you need to connect to the postgresql database container, run the following command:

      `docker exec -it multiple-auth-app-postgres bash`
        - Connect to postgresql database, once inside the container:

          `psql -U postgres`
2. **Start up the multiple-auth-api:**
    - Run the following command from the root directory of the project (or just press the start button in your IDE):

      `./mvnw spring-boot:run`

    - Default port for the _multiple-auth-api_ is 8080. If you want to change the port, set the "server.port" property in the _application.properties_ file. If you change the port, make sure to update the port in the multiple-auth-ui project as well (_constants.ts_ file, "API_BASE_URL" parameter).

        - As this project uses OAuth2, make sure to update the redirect-uri in the _application.properties_ file and in configurations at OAuth2 providers (Google, GitHub, Twitter, etc.) too.
    - If you checked out from _main_ branch, you will see that _application.properties_ contains references to environment variables. Make sure to create a _.env_ file in the root of the project, and list all the required properties there as key-value pairs (don't forget to exclude this file from version control):
      ```
      DATABASE_USER=postgres
      DATABASE_PASSWORD=K29r8Dhc79n2gPG86CRhoVt9NBxTa0Gk
      ...
      ```
3. **Start up the multiple-auth-ui:**
    - Run the following command from the root directory of the project:

      `npm install`
    - Run the following command from the root directory of the project:

      `ng serve`
    - Default port for the user-app-ui is 4200. If you want to change the port, update the port in the angular.json file. On the following path: _projects -> user-management-app-ui -> architect -> serve_ add this:
        ```
        "options": {
            "port": [desired-port]
        }
        ```
        - Make sure to update authorized redirect uri usage places in the _multiple-auth-api_ too.
4. **Register a user in the database**
    - Open the database (either from your IDE or command-line) and create a new record in _users_ table containing only the email address with which you are planning to log in to the application. Without a registered email address, you won't be allowed to log in. Some login providers also require to register test user email addresses upfront (Google, for example), so make sure you don't skip this step as well (view more information [here](https://blog.devgenius.io/part-3-implementing-authentication-with-spring-boot-security-6-oauth2-and-angular-17-via-8716646ed062)).
