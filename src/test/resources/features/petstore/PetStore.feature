Feature: End to end user journey for petstore

  Scenario: Create, read, update and delete pet
    Given a new pet is created
      | Id | categoryId | CategoryName | name   | status    | tagName  | tagId | PhotoUrl                                                                                                                                                       |
      | 11 | 1          | Dogs         | aDoggy | Available | aTagName | 0     | https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1.00xw:0.669xh;0,0.190xh&resize=1200:* |
    Then pet information is verified
    When pet attribute "name" is changed to "agoodboy"
    Then pet information is verified
    When pet attribute "status" is changed to "unavailable"
    Then pet information is verified
    When pet is deleted
    Then pet can no longer be found