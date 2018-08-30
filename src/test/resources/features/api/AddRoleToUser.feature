Feature: API - Add Role to user

  As an GDP Level or Account Level Administrator,
  I want to add a user to a role (enabling user/role combinations),
  so that there is flexibility as to which features different users can have access to

  Scenario: API - Add Role To user - Create and Deletes the user role
    Given Global Admin user log into Admin to get OktaToken
    When User requests to add role to the user
    Then Role is added to the user into response