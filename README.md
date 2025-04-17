
# ✅ REST API Testing with YAML, JUnit 5, and RestAssured

This project enables **automated API testing** using test cases defined in a **YAML file**, powered by **JUnit 5**, **RestAssured**, and optionally, **Allure Reporting**.

---

## 🔧 Project Overview

- Write all test scenarios in a single `testyaml.yml` YAML file.
- Use `ApiTest.java` to dynamically read and execute all test cases.
- Supports all REST methods: `GET`, `POST`, `PUT`, `DELETE`.

---

## 📁 Project Structure

RestAssured-Automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/RestAssured/RestAssuredCode/
│   │   │       ├── RestAssuredCodeApplication.java   # Main Spring Boot app (optional for test runs)
│   │   │       ├── Testcase.java                     # POJO class representing a test case
│   │   │       ├── TestcaseList.java                 # Wrapper class for list of test cases
│   │   │       └── YamlReader.java                   # Utility to read YAML test data
│   │   └── resources/
│   │       └── testyaml.yml                          # YAML file with all test case definitions
│
│   ├── test/
│   │   ├── java/
│   │   │   └── com/example/RestAssured/RestAssuredCode/
│   │   │       └── ApiTest.java                      # JUnit test runner for executing YAML test cases
│   │   └── resources/
│   │       ├── Integration.xlsx                      # (Optional) Excel input for conversion
│   │       └── schemaValidator.json                  # (Optional) JSON schema if needed for validation
│
├── pom.xml                                           # Maven build configuration
├── README.md                                         # Project documentation


## 📄 YAML Test Case Format

```yaml
- name: Create User
  method: POST
  endpoint: /api/users
  headers:
    Content-Type: application/json
  body: '{"name": "morpheus", "job": "leader"}'
  status: 201

- name: Get Users
  method: GET
  endpoint: /api/users?page=2
  headers:
    Content-Type: application/json
  body: ''
  status: 200
## How to Run Tests
Run Tests from IDE or Command Line:
bash
Copy
Edit
mvn clean test
This will read the testyaml.yml file and execute each test case using RestAssured.

📦 Dependencies (pom.xml)
Make sure you have these:

xml
Copy
Edit
<dependencies>
  <dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.3.1</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.3</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>2.0</version>
  </dependency>
  <dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-junit5</artifactId>
    <version>2.24.0</version>
    <scope>test</scope>
  </dependency>
</dependencies>
📊 Generate Allure Report (Optional)
allure serve target/allure-results


