Feature: Add and delete products from cart

  Scenario: Add and delete 3 products
    When '3' products are added in cart
    Then make checkout
    And wait '1' second(s)
    And delete all products from cart
    Then the cart is empty