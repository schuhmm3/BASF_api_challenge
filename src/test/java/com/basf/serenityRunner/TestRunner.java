package com.basf.serenityRunner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;

/**
 * Created by Usuario on 19/05/2017.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        glue = {"com.basf.gherkinDefinitions"}
)

public class TestRunner {

}
