package com.pragma.integration.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.pragma.integration.steps",
        "com.pragma.integration.tasks",
        "com.pragma.integration.questions",
        "com.pragma.integration.models",
        "com.pragma.integration.abilities"
    },
    plugin = {
        "pretty",
        "serenity:target/site/serenity/index.html",
        "json:target/serenity-reports/serenity.json"
    },
    monochrome = true,
    dryRun = false,
    tags = "@integration"
)
public class RunIntegrationTest {
}