package cucumber;

import io.cucumber.java8.En;

public class GeneralStepdefs extends CucumberTestBase implements En {
    public GeneralStepdefs() throws InterruptedException{

        And("^wait '(\\d+)' second\\(s\\)$", (Integer secs) -> {
            Thread.sleep(secs*1000);
        });
    }
}
