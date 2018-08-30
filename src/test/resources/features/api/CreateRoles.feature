Feature: API - Create Roles

  As an GDP Level or Account Administrator,
  I want to create groups of users aka roles
  so that different users can perform different duties on the platform by giving access to different combinations of features (using various permissions)

  Background:
    Given Global Admin user log into Admin to get OktaToken

  Scenario: API - Create Role - Creates a new role
    When User requests to create new role with "no parent":
      | name        | code    | type        | level        | domain |
      | NewRoleName | NewCode | NewRoleType | NewRoleLevel | snt    |
    Then Newly created Role definition retrieved into response

  Scenario: API - Create Role - Creates a new role with invalid parent id
    When User requests to create new role with "invalid parent id":
      | name        | code    | type        | level        | domain | parent    |
      | NewRoleName | NewCode | NewRoleType | NewRoleLevel | snt    | sntparent |
    Then error message "Bad Parent ID Format" throws into response

  Scenario: API - Create Role - Creates a new role with valid parent id
    When User requests to Get all roles definitions for "gdp" domain
    And User requests to create new role with "valid parent id":
      | name        | code    | type        | level        | domain | parent       |
      | NewRoleName | NewCode | NewRoleType | NewRoleLevel | snt    | valid_parent |
    Then Newly created Role definition retrieved into response

  Scenario Outline: API - Create Role - Creates a new role with blank values to fields
    When User requests to create new role with blank values to fields
      | name   | code   | type   | level   | domain   |
      | <name> | <code> | <type> | <level> | <domain> |
    Then error message "Please provide the required details for creating new role" throws into response

    Examples:
      | name        | code    | type        | level        | domain |
      | NewRoleName |         | NewRoleType | NewRoleLevel | snt    |
      | NewRoleName | NewCode |             | NewRoleLevel | snt    |
      | NewRoleName | NewCode | NewRoleType |              | snt    |
      | NewRoleName | NewCode | NewRoleType | NewRoleLevel |        |

  Scenario: API - Create Role - Create duplicate role ( Role Name and Code Same )
    When User requests to create new role with "no parent":
      | name        | code    | type        | level        | domain |
      | NewRoleName | NewCode | NewRoleType | NewRoleLevel | snt    |
    Then Newly created Role definition retrieved into response
    When User requests to create duplicate role
    Then error message "Role already exists" throws into response

  Scenario: API - Create Role - Create duplicate role with ( Same Role Name and different code )
    When User requests to create new role with "no parent":
      | name        | code    | type        | level        | domain |
      | NewRoleName | NewCode | NewRoleType | NewRoleLevel | snt    |
    Then Newly created Role definition retrieved into response
    When User requests to create duplicate role with same role name and different code
    Then error message "Role already exists" throws into response

  Scenario: API - Create Role - Create duplicate role with ( Different Role Name and Same code )
    When User requests to create new role with "no parent":
      | name        | code    | type        | level        | domain |
      | NewRoleName | NewCode | NewRoleType | NewRoleLevel | snt    |
    Then Newly created Role definition retrieved into response
    When User requests to create duplicate role with different role name and same code
    Then error message "Role already exists" throws into response