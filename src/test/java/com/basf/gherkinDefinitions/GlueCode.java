package com.basf.gherkinDefinitions;

import com.basf.serenitySteps.SerenitySteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.RandomStringUtils;

public class GlueCode {

    @Steps
    private SerenitySteps steps;

    @When("^I get all the people$")
    public void iRequestAll() {
        steps.getAll();
    }

    @When("^I create a person$")
    public void iRequestToCreateNewPerson() {
        steps.createPerson(false);
    }

    @When("^I create a person with an empty body$")
    public void iRequestToCreateNewPersonEmptyBody() {
        steps.createPerson(true);
    }

    @When("^I create a person without firstName$")
    public void iRequestToCreateNewPersonNoName() {
        steps.createPersonNoFirstName();
    }

    @Then("^I should get (.*) status code$")
    public void iShouldGetStatusCode(int expectedStatusCode) {
        steps.verifyStatusCode(expectedStatusCode);
    }

    @And("^The following response fields should exist and have not null value$")
    public void theResponseFieldsShouldExistAndHaveValue(DataTable expectedValues) {
        for (String key : expectedValues.asList(String.class)) {
            steps.verifyKeyHasValue(key);
        }
    }

    @When("^I try to retrieve previously created person$")
    public void iTryToRetrievePreviousCreatedPerson() {
        final String personId = steps.getResponseNodeValue("id");
        steps.getPersonById(personId);
    }

    @When("^I try to retrieve non-created person$")
    public void iTryToRetrieveNonCreatedPerson() {
        final String personId = RandomStringUtils.random(20, true, true);
        steps.getPersonById(personId);
    }

}
