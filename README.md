## Automated tests using Serenity, Cucumber and Maven

This project contains automation tests for tvstack application and many other projects under enablers

Run the tests like this:

```
mvn clean verify
```
By default, the tests run against DEV environment but you can override it by passing env property as below. Configuration details exist in env.conf file under resources folder
```
mvn clean verify -Denv=demo
```

By default, the tests run on Chrome (assuming windows) in headless mode. If you need to run with other platforms/browsers, modify the serenity.properties file or run the tests like this:
```
mvn clean verify -Dwebdriver.driver=firefox
```
The reports will be generated in `target/site/serenity`.
