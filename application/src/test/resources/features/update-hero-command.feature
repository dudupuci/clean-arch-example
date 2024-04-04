Feature: Update Hero Command

  Scenario: Update Hero Command
    Given a hero with full args to update
    When create a new update command
    And assert hero is updated
    Then the update command should be not null