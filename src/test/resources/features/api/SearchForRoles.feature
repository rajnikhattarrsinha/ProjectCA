Feature: API - Search for Roles information

  As an GDP Level or Account Level Administrator,
  I want to search for a role
  so that I can review or perform further actions on that role

  Background:
    Given Global Admin user log into Admin to get OktaToken

  Scenario: API - Search Roles - Search roles definitions for gdp domain
    When User requests to Get all roles definitions for "gdp" domain
    Then gdp domain Roles definitions including type and level are retrieved into response
      | name                                           | code         | type          | level          | domain |
      | Super User                                     | superuser    | SuperAdmin    |                | gdp    |
      | Platform Admin                                 | platfadmin   | admin         | platform       | gdp    |
      | Market User Admin                              | marketadmin  | admin         | market         | gdp    |
      | Client User Admin                              | clientadmin  | admin         | client         | gdp    |
      | Brand User Admin                               | brandadmin   | admin         | brand          | gdp    |
      | Policy Admin                                   | policyadmin  | admin         | policy         | gdp    |
      | Commercial Owner (of clients)                  | comrcowner   | owner         | client         | gdp    |
      | Platform Account Owner                         | accntowner   | owner         | account        | gdp    |
      | Client Legal                                   | clientlegal  | legal         | client         | gdp    |
      | Finance Business Partner                       | finbp        | bpart         | client         | gdp    |
      | Platform Account User Admin                    | accntadmin   | admin         | account        | gdp    |
      | Connector User                                 | conctruser   | user          | connector      | gdp    |
      | Taxonomy Management and Integration User Admin | taxintadmin  | admin         | taxonomy       | gdp    |
      | Storage and Visualisation User Admin           | stovisadmin  | admin         | storage        | gdp    |
      | Transformation User Admin                      | transfadmin  | admin         | transformation | gdp    |
      | Referene Data Manager                          | refdatmgr    | manager       | data           | gdp    |
      | Naming Convention Manager                      | namconmgr    | manager       | convention     | gdp    |
      | Naming Instance Manager                        | naminsmgr    | manager       | instance       | gdp    |
      | 3rd Party Integration Manager                  | integrmgr    | manager       | integration    | gdp    |
      | Storage Data Analyst                           | stordanlst   | dataanalyst   | storage        | gdp    |
      | Sr Data Analyst/Data Scientist                 | stordscnt    | datascientist | storage        | gdp    |
      | Transformation Data Analyst                    | transfdanlst | dataanalyst   | transformation | gdp    |
      | Governance and Security Owner                  | govsecowner  | owner         | transformation | gdp    |
      | Account Owner                                  | acctowner    | owner         | transformation | gdp    |

  Scenario: API - Search Roles - Search roles definitions for domain which does not exist
    When User requests to Get all roles definitions for "dan" domain
    Then Roles definitions returned empty for "dan" domain into response

  Scenario: API - Search Roles - Search roles definitions for Super User id
    When User requests to Get all roles definitions for "gdp" domain
    And User requests to Get roles definitions for Super User
    Then Super User Role definition including type and level are retrieved into response

  Scenario: API - Search Roles - Search roles definitions for Platform Admin name
    When User requests to Get all roles definitions for "Platform Admin" name
    Then Platform Admin name Role definition including type and level are retrieved into response

  Scenario: API - Search Roles - Search roles definitions for Storage and Visualisation User Admin code
    When User requests to Get all roles definitions for "stovisadmin" code
    Then Storage and Visualisation User Admin code Role definition including type and level are retrieved into response

  Scenario: API - Search Roles - Search roles definitions for Account Owner type and Code
    When User requests to Get all roles definitions for "owner" type and "acctowner" code
    Then Account Owner type and Code Role definition including type and level are retrieved into response

  Scenario: API - Search Roles - Search roles definitions for Platform Account Owner type and level
    When User requests to Get all roles definitions for "owner" type and "account" level
    Then Platform Account Owner type and level Role definition including type and level are retrieved into response
