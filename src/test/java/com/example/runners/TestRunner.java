package com.example.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.example.stepdefinitions", "com.example.hooks"},
    tags = "@login or @test",
    plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"}
)
public class TestRunner {
    // JUnit runner for Cucumber
}
