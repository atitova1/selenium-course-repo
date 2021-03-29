package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, glue = {"src/test/java/cucumber"}, features = "/Users/ruatoam/Developer/GIT/selenium-tests/selenium-course-repo/src/test/resources/cucumber/addProducts.feature")
public class RunSpecs {
}
