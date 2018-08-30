Feature: API - Delete Roles

  As an GDP Level or Account Level Administrator
  I want to delete a role
  So that  I can reduce the groups of users and associated permissions profiles in the system

  Background:
    Given Global Admin user log into Admin to get OktaToken

  Scenario: API - Delete Role - Delete a role
    When User requests to create new role with "no parent":
      | name        | code    | type        | level        | domain |
      | NewRoleName | NewCode | NewRoleType | NewRoleLevel | snt    |
    Then Newly created Role definition retrieved into response
    When User requests to delete a role
    Then role is deleted from the system
    When User requests to search for deleted role
    Then error message "Bad role ID" throws into response