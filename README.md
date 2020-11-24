#  BASF QA API Automation Code Challenge

## Tool Stack
The selected framework is SerenityBDD http://www.thucydides.info/.

> Serenity BDD helps you write cleaner and more maintainable automated acceptance and regression tests faster. Serenity also uses the test results to produce illustrated, narrative reports that document and describe what your application does and how it works.

Under the hood, Serenity provides you out-of-the-box reporting capabilities and support for writing BDD scenarios with Gherkin that will run under Cucumber. 

The programming language of election is Java, one of the most spread languages in test automation projects.

Serenity also offers integration with RestAssured, a well-known Java library used for API test automation.

I also evaluated the possibility of using Postman (that can be run from command-line with Newman tool). Even though I finally discarded this option, you can find the collection and the environment that I created for exploratory testing under PostmanCollection folder. You can import those files for being used.

## How to deploy and run the tests 
  
**Execution instructions**
-------------  

For running your tests the only thing you should do is downloading the test project from the provided repo.  
  
Then you should be able to run the automated tests just by typing from the directory you downloaded the tests:  
  
* Linux/Mac:  
  
    ./gradlew clean test aggregate (-Dcucumber.options="--tags @tag")  
  
* Windows:  
  
    gradlew.bat clean test aggregate (-Dcucumber.options="--tags @tag")"  
  
(-Dcucumber.options="--tags @tag") is optional and will allow you to run a subset of tests. If not specified all the tests will be run  
  
Available tags are:  
* @positive  
* @negative

**Command line examples**
-------------  
./gradlew clean test aggregate
./gradlew clean test aggregate -Dcucumber.options="--tags @positive"

## Expected output

After finishing, you should be able to open the report file and analyse the results  
  
./target/site/serenity/index.html  

## Found issues

While exploring and automating the provided API at http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com/api/person I found some issues that I'd report as bugs to be reviewed.

* **Severity**: Blocker. **Priority**: High

    You're asked to validate correctness of the basic CRU (create, read, update) operations. However, no update endpoint is provided. Or, at least, it's not documented.

* **Severity**: Major. **Priority**: Medium

    PUT is being used for creating a new person. However, by definition, PUT should be idempotent. That means that response should always be the same
    
    However, every time we send a PUT request, we're getting a different id.
    
    So, I'd suggest using POST for create requests and PUT/PATCH for update ones.
	> https://restfulapi.net/idempotent-rest-apis/

* **Severity**: Major. **Priority**: Medium

    When creating a person via PUT request no validation is being performed in the JSON body being used. So, e.g. you can send a body where a field is missing or you can add a non-expected field.
    
    That means, that at any point in time, you could break the contract (interface) with your clients without noticing it.

* **Severity**: Blocker. **Priority**: High

    No DELETE endpoint is provided. 
    
    That means that you're creating objects but since there's no way to delete them the degradation of the performance is getting worse and worse to the point that the whole system can become unresponsive e.g. while performing Load Tests.
    
    
    
    # Results
    
    
```
user@azure:~/challenges/test/aterrong/BASF_api_challenge$ gradle clean test aggregate

> Task :test

com.basf.serenityRunner.TestRunner STANDARD_OUT
    @person @smoke
    Feature: Basic API feature - Person API Crud

      @person @smoke @positive
      Scenario: Get all                                                        # src/test/resources/features/Person.feature:5

com.basf.serenityRunner.TestRunner > Basic API feature - Person API Crud.Get all STANDARD_OUT
        Given I get all the people                                             # GlueCode.iRequestAll()
        Then I should get 200 status code                                      # GlueCode.iShouldGetStatusCode(int)
        And The following response fields should exist and have not null value # GlueCode.theResponseFieldsShouldExistAndHaveValue(DataTable)

com.basf.serenityRunner.TestRunner STANDARD_OUT

      @person @smoke @positive
      Scenario: Create a new person                                            # src/test/resources/features/Person.feature:14

com.basf.serenityRunner.TestRunner > Basic API feature - Person API Crud.Create a new person STANDARD_OUT
        Given I create a person                                                # GlueCode.iRequestToCreateNewPerson()
        Then I should get 200 status code                                      # GlueCode.iShouldGetStatusCode(int)
        And The following response fields should exist and have not null value # GlueCode.theResponseFieldsShouldExistAndHaveValue(DataTable)

com.basf.serenityRunner.TestRunner STANDARD_OUT

      @person @smoke @positive
      Scenario: Retrieve an existing person                                    # src/test/resources/features/Person.feature:21

com.basf.serenityRunner.TestRunner > Basic API feature - Person API Crud.Retrieve an existing person STANDARD_OUT
        Given I create a person                                                # GlueCode.iRequestToCreateNewPerson()
        When I try to retrieve previously created person                       # GlueCode.iTryToRetrievePreviousCreatedPerson()
        Then I should get 200 status code                                      # GlueCode.iShouldGetStatusCode(int)
        And The following response fields should exist and have not null value # GlueCode.theResponseFieldsShouldExistAndHaveValue(DataTable)

com.basf.serenityRunner.TestRunner STANDARD_OUT

      @person @smoke @negative
      Scenario: Retrieve a non-existing person     # src/test/resources/features/Person.feature:31

com.basf.serenityRunner.TestRunner > Basic API feature - Person API Crud.Retrieve a non-existing person STANDARD_OUT
        Given I try to retrieve non-created person # GlueCode.iTryToRetrieveNonCreatedPerson()
        Then I should get 404 status code          # GlueCode.iShouldGetStatusCode(int)

com.basf.serenityRunner.TestRunner STANDARD_OUT

      @person @smoke @negative
      Scenario: Create a new person with an empty body # src/test/resources/features/Person.feature:37

com.basf.serenityRunner.TestRunner > Basic API feature - Person API Crud.Create a new person with an empty body STANDARD_OUT
        Given I create a person with an empty body     # GlueCode.iRequestToCreateNewPersonEmptyBody()
        Then I should get 400 status code              # GlueCode.iShouldGetStatusCode(int)

com.basf.serenityRunner.TestRunner STANDARD_OUT

      @person @smoke @negative
      Scenario: Create a new person without fisrtName                          # src/test/resources/features/Person.feature:43

com.basf.serenityRunner.TestRunner > Basic API feature - Person API Crud.Create a new person without fisrtName STANDARD_OUT
        Given I create a person without firstName                              # GlueCode.iRequestToCreateNewPersonNoName()
        Then I should get 200 status code                                      # GlueCode.iShouldGetStatusCode(int)
        And The following response fields should exist and have not null value # GlueCode.theResponseFieldsShouldExistAndHaveValue(DataTable)

com.basf.serenityRunner.TestRunner STANDARD_OUT

    6 Scenarios (6 passed)
    17 Steps (17 passed)
    0m5.565s


> Task :aggregate
Generating Serenity Reports for BASF-api-challenge to directory /home/user/challenges/test/aterrong/BASF_api_challenge/target/site/serenity


BUILD SUCCESSFUL in 9s
7 actionable tasks: 7 executed
```
