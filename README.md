# app-development-prototype-webflux-microservice
    Spring WebFlux supports two types of programming models :
    1.Traditional annotation-based model with @Controller, @RequestMapping, and other annotations that you have been using in Spring MVC.
    2.A brand new Functional style model based on Java 8 lambdas for routing and handling requests
    
    This sample project uses the first approach because we want to make use of the server/client stubs generated with openapi spec

## project initial setup
    Add supressions.xml for maven-checkstyle-plugin
    Add .mvn/jvm.config to fix issue with git-code-format-maven-plugin . check https://github.com/Cosium/git-code-format-maven-plugin
    Add lombok.config for lombok
    Add .gitignore
## Project IDE initial setup
    Add google-java-format plugin to intellij
    Add google code style for intellij from https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml
    [Optional] Add spotbugs plugin https://plugins.jetbrains.com/plugin/14014-spotbugs
    [Optional] Add sonarlint plugin https://plugins.jetbrains.com/plugin/7973-sonarlint
    [Optional] Add GitToolBox plugin https://plugins.jetbrains.com/plugin/7499-gittoolbox

## Project setup 
    Add the following properties for genearting open-api client and server code 
    open-api-server-package
    open-api-client-package

## Build and Start the application
    Build application 
        * mvn clean install
    Build application skip integration test
        * mvn clean install -Dskip.integration.test=true
    Run application using 
        * mvn spring-boot:run

## swagger ui endpoint
    http://localhost:8886/webjars/swagger-ui/index.html
## spring actuator 
    http://localhost:8886/actuator
