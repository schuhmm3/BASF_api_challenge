package com.basf.serenitySteps;

import com.basf.config.Configuration;
import com.basf.support.RequestHelper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.InputStream;

import static net.serenitybdd.rest.SerenityRest.rest;

public class SerenitySteps {

    private RequestHelper requestHelper = new RequestHelper();

    // Prepare request
    private RequestSpecification spec = rest().auth()
                .preemptive()
                .basic(Configuration.USER, Configuration.PASSWORD)
                .baseUri(Configuration.URI)
                .contentType(ContentType.JSON)
                .when();

    public String getEndpoint() {
        return Configuration.PERSON;
    }

    /**
     * Performs a GET operation that returns all the existing persons
     */
    @Step
    public void getAll() {
        requestHelper.executeRequest(spec, "GET", getEndpoint() + "all");
    }

    /**
     * Creates a new person without firstName
     */
    @Step
    public void createPersonNoFirstName() {
        callCreatePerson(false, "create_person_missing_field.json");
    }

    /**
     * Creates a new person
     * @param isEmptyBody bolean tru/false depending on whether we want an empty body to be used
     */
    @Step
    public void createPerson(boolean isEmptyBody) {
        callCreatePerson(isEmptyBody, "create_person.json");
    }

    /**
     * Performs a PUT operation that will create a new person
     */
    @Step
    public void callCreatePerson(boolean isEmptyBody, String resource) {
        try {
            if (!isEmptyBody) {
                InputStream is = this.getClass().getResourceAsStream("/requests/" + resource);
                JSONObject body = requestHelper.jsonInputStreamToJsonObject(is);
                spec = spec.body(body.toMap());
            }
            requestHelper.executeRequest(spec, "PUT", getEndpoint());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Performs a GET operation with an ID provided by parameter from the scenario
     *
     * @param id The ID of a user
     */
    @Step
    public void getPersonById(String id) {
        String endpoint = getEndpoint() + id;
        requestHelper.executeRequest(spec, "GET", endpoint);

    }

    /**
     * Verify last status code
     * @param expectedStatusCode Expected status code in the response
     */
    @Step
    public void verifyStatusCode(int expectedStatusCode) {
        Assert.assertEquals("status code doesn't match", expectedStatusCode, getLastStatusCode());
    }

    /**
     * @return Status code for the last request
     */
    public int getLastStatusCode() {
        return getLastResponse().getStatusCode();
    }

    /**
     * Verify the value of a node is not null
     * @param key node to be evaluated
     */
    @Step
    public void verifyKeyHasValue(String key) {
        Assert.assertNotNull("Value for " + key + " doesn't exists or is null", getValueFromKey(key));

    }

    /**
     * Return the value of a node from last response body
     * @param key node to be evaluated
     */
    public String getValueFromKey(String key) {
        return getLastResponse().getBody().jsonPath().getString(key);
    }

    /**
     * @return last Response from a request
     */
    public Response getLastResponse() {
        return Serenity.sessionVariableCalled("response");
    }

    /**
     * @param key JSON node to return
     * @return value of key
     */
    public String getResponseNodeValue(String key) {
        JsonPath j = new JsonPath(getLastResponse().asString());
        return j.getString(key);
    }
}
