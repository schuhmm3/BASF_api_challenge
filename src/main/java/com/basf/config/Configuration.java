package com.basf.config;

public class Configuration {

    //Base URI
    public static final String URI = "http://azure-qknows-prod-challenges-1.northeurope.cloudapp.azure.com";

    //Endpoints
    public static final String PERSON = "/api/person/";

    // Basic auth
    public static final String USER = "user";
    public static final String PASSWORD = "pwd!";

    //Session Variable Response
    public static final String SESSION_RESPONSE = "response";

    private Configuration() {
    }
}