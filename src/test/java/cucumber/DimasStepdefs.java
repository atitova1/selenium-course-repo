package cucumber;

import io.cucumber.java8.En;
import pages.YandexPage;

public class DimasStepdefs   extends CucumberTestBase implements En {
    public DimasStepdefs() {
        When("^yandex page is opened and search is made$", () -> {
            app.yandexSearch();
        });

        Then("^result pages are shown$", () -> {
            app.flatsAreFound();
        });

        And("^every not-domcklick site is visited$", () -> {
            app.visitEverySite();
        });
    }
}
