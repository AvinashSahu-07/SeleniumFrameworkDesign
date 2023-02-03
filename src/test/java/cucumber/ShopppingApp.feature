
@tag
Feature: Purchase the order from Ecommerce webapp

Background:
Given: I landed on Ecommerce page

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <email> and password <password>
    When Add product <itemName> to cart
    And Checkout <itemName> and place the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page.
    
    Examples: 
      | email  									| password 	| itemName 	 |
      | test17@mailinator.com   | Test@1234 | ZARA COAT 3|
