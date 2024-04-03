Feature: Create Hero Command

  Scenario: Create Hero Command
    Given a hero with full args to create
    When create a new create command
    Then the create command should be not null