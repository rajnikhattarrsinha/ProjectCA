Feature: API - Get user details

  As a user
  I wanted to view my user profile details
  so that I clearly see who is logged in and be able to validate my details are correct


  Scenario: API - Get User Details - Returns details about the user matching the given id, including related user roles
    Given Global Admin user log into Admin to get OktaToken
    When User requests to Get the User details for given id "Subhani.Shaik@dentsuaegis.com"
    Then Users matching details including user roles are returned into response

  Scenario: API - Get User Details - Returns details about the Global admin logged in user, including related user roles
    Given Global Admin user log into Admin to get OktaToken
    When Users requests to Get details about the logged in user
    Then Users details about the Global admin logged in user are returned into response

  Scenario: API - Get User Details - Returns details about the Market admin logged in user, including related user roles
    Given Market Admin user log into Admin to get OktaToken
    When Users requests to Get details about the logged in user
    Then Users details about the Market admin logged in user are returned into response

  Scenario: API - Get User Details - Returns details about the Client admin logged in user, including related user roles
    Given Client Admin user log into Admin to get OktaToken
    When Users requests to Get details about the logged in user
    Then Users details about the Client admin logged in user are returned into response