Feature: Delete Hero Command

  Scenario: Delete Hero Command
    Given a hero with full argz
    When create a new delete command
    Then the delete command should be not null