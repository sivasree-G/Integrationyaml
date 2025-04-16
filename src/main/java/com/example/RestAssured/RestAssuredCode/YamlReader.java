package com.example.RestAssured.RestAssuredCode;//package com.example.restassured;

import com.example.RestAssured.RestAssuredCode.TestcaseList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

public class YamlReader {

    public TestcaseList readYaml(String fileName) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        InputStream input = new ClassPathResource(fileName).getInputStream();
        return mapper.readValue(input, TestcaseList.class);
    }
}
