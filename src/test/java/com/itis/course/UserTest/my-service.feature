Feature: Features for my dao implementation

    Background:
        Given my UserRepository class exists

    Scenario: Get users count from my db
        When get users count
        Then users count should be positif number

    Scenario: Get users list
        When get all users
        Then users should be list of size zero or plus