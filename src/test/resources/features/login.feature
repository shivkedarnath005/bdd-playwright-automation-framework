Feature: Login functionality

  @login
  Scenario: Valid user login
    Given User launches URL
    When User enters login email and password
    And User clicks on Login
    Then User verifies Automation Exercise logo on Home Page