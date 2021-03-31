package cucumber;

import io.cucumber.java8.En;

public class CartStepdefs  extends CucumberTestBase implements En {
    public CartStepdefs() {
        When("^'(\\d+)' products are added in cart$", (Integer productsCount) -> {
            app.addProducts(productsCount);
        });

        Then("^make checkout$", () -> {
            app.makeCheckout();
        });

        Then("^delete all products from cart$", () -> {
            app.deleteAllProducts();
        });
        Then("^the cart is empty$", () -> {
            app.isCartEmpty();
        });

    }
}
