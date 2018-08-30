Feature: API - Send notification to the user

  As a Administrator
  I want to send a new notification
  So that I can send messages to users on the fly and not be restricted by any existing notifications

  Background:
    Given Global Admin user log into Admin to get OktaToken

  Scenario: API - Send notification - Send notification on the fly

    When User request to send notification
     | to                                   | from                         | html   | subject |
     | naman.gupta@successivesoftwares  | naman.gupta@successive.tech  | test   | test    |

    Then Notification sent





