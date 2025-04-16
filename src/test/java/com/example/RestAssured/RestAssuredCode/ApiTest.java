package com.example.RestAssured.RestAssuredCode;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@Epic("API Testing")
@Feature("YAML-Driven API Tests")
public class ApiTest {

    @Test
    @DisplayName("Run API tests from YAML file")

    void runApiTestsFromYaml() throws Exception {
        RestAssured.baseURI = "https://reqres.in";

        YamlReader reader = new YamlReader();
        TestcaseList testList = reader.readYaml("testyaml.yml");

        int pass = 0;
        int fail = 0;

        for (Testcase test : testList.testcases) {
            System.out.println("▶ " + test.method + " " + test.endpoint);

            Response response = null;

            switch (test.method.toUpperCase()) {
                case "GET":
                    response = RestAssured.given()
                            .filter(new io.qameta.allure.restassured.AllureRestAssured())  // <-- Add this line
                            .headers(test.headers)

                            .get(test.endpoint);
                    break;
                case "POST":
                    response = RestAssured.given()
                            .filter(new io.qameta.allure.restassured.AllureRestAssured())  // <-- Add this line
                            .headers(test.headers)

                            .body(test.body)
                            .post(test.endpoint);
                    break;
                case "PUT":
                    response = RestAssured.given()
                            .filter(new io.qameta.allure.restassured.AllureRestAssured())  // <-- Add this line
                            .headers(test.headers)

                            .body(test.body)
                            .put(test.endpoint); // ✅ correct method
                    break;
                case "PATCH":
                    response = RestAssured.given()
                            .filter(new io.qameta.allure.restassured.AllureRestAssured())  // <-- Add this line
                            .headers(test.headers)

                            .body(test.body)
                            .patch(test.endpoint);
                    break;


                case "DELETE":
                    response = RestAssured.given()
                            .headers(test.headers)
                            .delete(test.endpoint); // ✅ DELETE usually doesn't need a body
                    break;

                // Add PUT, DELETE etc. if needed

                default:
                    System.out.println("⚠ Unsupported method: " + test.method);
                    continue;
            }

            int actualStatus = response.getStatusCode();
            if (actualStatus == test.status) {
                System.out.println("✅ Passed (Status: " + actualStatus + ")");
                pass++;
            } else {
                System.out.println("❌ Failed (Expected: " + test.status + ", Got: " + actualStatus + ")");
                fail++;
            }

            System.out.println("--------------------------------------------------");
        }

        // Final summary
        System.out.println("✔ Total Passed: " + pass);
        System.out.println("✘ Total Failed: " + fail);

        // Optional: assert all passed if you want to fail the test on any failure
        assert fail == 0 : "Some tests failed!";
    }
}
