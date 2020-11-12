package com.basf.gherkinDefinitions;

import com.basf.serenitySteps.SerenitySteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class GlueCode {

    @Steps
    private SerenitySteps steps;

    @When("^I request to get all the people$")
    public void iRequestAll() {
        steps.getAll();
    }

    @When("I request to create a person")
    public void iRequestToCreateNewPerson() {
        steps.createPerson();
    }

    @Then("I should get (.*) status code")
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

}
