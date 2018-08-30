Feature: API - Search for an OKTA user

  As an GDP Level or Account Level Administrator,
  I want to find a user in OKTA,
  so that the user can be used in GDP

  Scenario: API - Search User - Returns all users available in active directory (including the ones being unassigned to the application, up to a maximum of 100)
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get users available in active directory
    Then Users are retrieved into response

  Scenario: API - Search User - Returns the query details from active directory (Search for a user based on first name)
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get query for Users "Subhani" details
    Then Users details are retrieved into response

  Scenario: API - Search User - Returns the query details from active directory (Search for a user based on last name)
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get query for Users "Shaik" details
    Then Users details are retrieved into response

  Scenario: API - Search User - Returns the query details when user is not in active directory
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get query for Users "abcdef123" details
    Then Users details are not retrieved into response
