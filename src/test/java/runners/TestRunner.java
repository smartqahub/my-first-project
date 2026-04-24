package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},
    monochrome = true,
    
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber.json", // 👉 Jenkins ke liye important
        
        "junit:target/cucumber.xml",  // 👉 Jenkins report parsing ke liye MUST
        
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    }    
    )

public class TestRunner {
}
