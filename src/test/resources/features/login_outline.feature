Feature: Login functionality

  @test
  Scenario Outline: User logs in and verifies home page logo
    Given User launches URL "<url>"
    When User enters login email "<email>" and password "<password>"
    And User clicks on Login
    Then User verifies Automation Exercise logo on Home Page

    Examples:
      | url                                      | email                   | password  |
      | https://www.automationexercise.com/login | amoldeokar@gmail.com    | Test@123  |
