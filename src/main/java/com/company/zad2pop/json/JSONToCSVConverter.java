package com.company.zad2pop.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;

import static com.company.zad2pop.csv.Flattener.flatten;
import static com.company.zad2pop.PageReader.getPageContent;


public class JSONToCSVConverter {

    private static final File setCSVFilePath = new File("src/main/resources/dane.csv");
    private static final CsvMapper csvMapper = new CsvMapper();
    private static final CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
    private static final CsvSchema csvSchema = csvSchemaBuilder
            .build()
            .withHeader();

    public static void jsonToCSV(int size) throws IOException {
        JSONArray json = getPageContent(size);
        JsonNode jsonTree = nodeFlatten(json);
        JsonNode firstObject = jsonTree.elements().next();

        firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);

        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(setCSVFilePath, jsonTree);
        System.out.println(csvMapper);
    }

    private static JsonNode nodeFlatten(JSONArray node) throws JsonProcessingException {
        return flatten(convertArrayToNode(node));
    }

    private static JsonNode convertArrayToNode(JSONArray array) throws JsonProcessingException {
        return new ObjectMapper().readTree(String.valueOf(array));
    }
}
