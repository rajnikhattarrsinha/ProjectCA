@component:Login Test
Feature: Login Test
  As a User with valid credentials
  I have access to login to application

  Scenario: To Test Login functionality with valid credentials
    When I login as 'UserName' user with password 'Password'
    Then I should see user's home page

  Scenario: To Test Login functionality with valid username and invalid password
    When I login as 'UserName' user with password 'Password'
    Then I should see Sign in failed



