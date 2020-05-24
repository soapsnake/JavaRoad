package com.soapsnake.lab.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**

 * Created on 2019-12-31
 */
public class DatabindTester {

    public static void main(String[] args) throws IOException {
        JsonNodeFactory factory = JsonNodeFactory.withExactBigDecimals(true);
        List<JsonNode> list = new ArrayList<>();

        ObjectMapper mapper = getMapper();
        ObjectReader reader = mapper.reader();
        String json1 = "";
        JsonNode node1 = reader.readTree(json1);
        list.add(node1);

        String json2 = "";
        JsonNode node2 = reader.readTree(json2);
        list.add(node2);
        ArrayNode arrayNode = new ArrayNode(factory, list);

        System.out.println("2 = " + Integer.toBinaryString(2));
        System.out.println("10 = " + Integer.toBinaryString(10));
        System.out.println(2 ^ 10);
        System.out.println("8 = " + Integer.toBinaryString(8));

    }

    private static ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }
}
