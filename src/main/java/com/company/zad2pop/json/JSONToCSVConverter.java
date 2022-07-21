package com.company.zad2pop.json;

import com.company.zad2pop.PageReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;

public class JSONToCSVConverter {

    private final File csvFilePath = new File("src/main/resources/dane.csv");

    public void jsonToCSV(int size) throws IOException {
        PageReader pageReader = new PageReader();
        JSONArray json = pageReader.getPageContent(size);
        JsonNode jsonNode = nodeFlatten(json);
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        jsonNode.elements().next().fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);

        CsvSchema csvSchema = csvSchemaBuilder
                .build()
                .withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(csvFilePath, jsonNode);
    }

    public JsonNode nodeFlatten(JSONArray node) throws JsonProcessingException {
        Flattener flattener = new Flattener();
        return flattener.getFlattenedJson(convertArrayToNode(node));
    }

    public JsonNode convertArrayToNode(JSONArray array) throws JsonProcessingException {
        return new ObjectMapper().readTree(String.valueOf(array));
    }
}
