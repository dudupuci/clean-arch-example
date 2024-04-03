Feature: Comparision Use Case

  Scenario: Comparing heroes
    Given a hero with ID "heroId1"
    And another hero with ID "heroId2"
    When I compare the heroes
    Then the comparison output should contain the correct hero IDs
