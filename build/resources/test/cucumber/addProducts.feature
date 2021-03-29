Feature: Add and delete products from cart

  Scenario: Add 3 products
    When '3' products are added in cart
    Then make checkout
    And wait '1' second(s)
    Then delete all products from cart