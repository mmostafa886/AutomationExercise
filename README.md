# AutomationExercise


## Test Execution
1. Execute All Tests: mvn clean test 
2. Run Specific Test class: mvn clean test -Dtest=ApiTest 
3. Run Specific Test inside a class: mvn clean test -Dtest=MixedTest#registerNewUserAPIAndLoginTest

## Headless Execution
1. Can be done by modifying the properties file `src/main/resources/myProps/WebCapabilities.properties` and change the parameter `headlessExecution=true`
2. Headless mode is not available for Safari even if using the configuration in the previous step.


## Parallel Execution
1. Hint:
Remember to use private static ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>(); to properly manage your driver in your test classes as following the usual driver initialization methods will result in problems in managing drivers' sessions & immature tests ending.
2. Hint (Parallel Execution Mode):
   - OFF → Tests classes and Test Methods will run in sequence.
   - CLASSES → Test classes will run in parallel, and inside each class the methods will run in sequence.
   - METHODS → Test methods will run in parallel, and your classes will run in sequence. 
3. Change the properties file `src/main/resources/properties/TestNG.properties` to contain:
```
setParallel=METHODS
setThreadCount=3
setVerbose=1
setPreserveOrder=true
setGroupByInstances=true
setDataProviderThreadCount=1
```

## Docker Execution(With Cross-Browser testing)
1. Make sure that `"Docker Desktop"` is installed on your local machine.
2. Make sure that the project directory is writable (as the docker execution results in writing logs to the directory `src/test/resources/testDataFiles/target/logs/log4j.log`).
3. Change the properties file `src/main/resources/properties/ExecutionPlatform.properties` to contain: 
```
SHAFT.CrossBrowserMode=sequential
executionAddress=dockerized
targetOperatingSystem=LINUX
```
4. It is better to use headless mode in `src/main/resources/properties/WebCapabilities.properties`
```aiignore
headlessExecution=rue
```
5. For execution, it is better to use terminal run commands like: `mvn clean test -Dtest=DockerizedTest` instead of using the Intellij Class & test runner.
6. The Safari docker test are always failing due to issue with the Safari docker image `(Can't be solved, latest image is very old & this is something known for Safari & docker)`
