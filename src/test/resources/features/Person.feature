@person @smoke
Feature: Basic API feature - Person API Crud


  Scenario: Get all
    Given I request to get all the people
    Then I should get 200 status code

  Scenario: Create a new person
    Given I request to create a person
    Then I should get 200 status code
    And The following response fields should exist and have not null value
      | id |

  Scenario: Retrieve an existing person
    Given I request to create a person
    When I try to retrieve previously created person
    Then I should get 200 status code
    And The following response fields should exist and have not null value
      | id        |
      | firstName |
      | lastName  |





