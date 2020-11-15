@person @smoke
Feature: Basic API feature - Person API Crud

  @positive
  Scenario: Get all
    Given I get all the people
    Then I should get 200 status code

  @positive
  Scenario: Create a new person
    Given I create a person
    Then I should get 200 status code
    And The following response fields should exist and have not null value
      | id |

  @positive
  Scenario: Retrieve an existing person
    Given I create a person
    When I try to retrieve previously created person
    Then I should get 200 status code
    And The following response fields should exist and have not null value
      | id        |
      | firstName |
      | lastName  |

  @negative
  Scenario: Retrieve a non-existing person
    Given I try to retrieve non-created person
    Then I should get 404 status code


  @negative
  Scenario: Create a new person with an empty body
    Given I create a person with an empty body
    Then I should get 400 status code

  @negative
# To be discussed: IMHO this test should fail
  Scenario: Create a new person without fisrtName
    Given I create a person without firstName
    Then I should get 200 status code
    And The following response fields should exist and have not null value
      | id       |




