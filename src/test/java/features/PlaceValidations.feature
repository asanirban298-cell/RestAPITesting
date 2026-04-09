Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if place is successfully added with AddPLace API
    Given Create Payload for AddPlace API with <Accuracy>, "<Address>", "<Language>"
    When Call "AddPlaceAPI" with "Post" http request
    Then API call is successful with status code 200
    And Response body contains "status" with value "OK"
    And Response body contains "scope" with value "APP"
    And Validate "<Address>" with data from "GetPlaceAPI"

    Examples:
      | Accuracy | Address                         | Language |
      | 50       | 31, HRBR layout, New Delhi 6    | English  |
      | 55       | 41, Gurguntapalya layout, BLR 6 | Kannada  |

  @DeletePlace
  Scenario: Delete place with DeletePlace API
    Given Create Payload for DeletePlace API
    When Call "DeletePlaceAPI" with "Post" http request
    Then API call is successful with status code 200
    And Response body contains "status" with value "OK"
