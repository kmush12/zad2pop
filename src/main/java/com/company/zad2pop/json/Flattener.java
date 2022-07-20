package com.company.zad2pop.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class Flattener {

    private static final Map<String, JsonNode> namesAndValues = new HashMap<>();
    private static final List<Map<String,JsonNode>> namesAndObjects = new ArrayList<>();
    private static final ObjectMapper objectMapper = getDefaultObjectMapper();
    private static ObjectMapper getDefaultObjectMapper(){return new ObjectMapper();}

    public static JsonNode getFlattenedJson(JsonNode jsonNode){
        Iterator<JsonNode> jsonObjects = jsonNode.elements();
        for(int i=0; i<jsonNode.size(); i++){
            JsonNode jsonObject = jsonObjects.next();
            namesAndObjects.add(flattenJson(jsonObject));
        }
        return objectMapper.valueToTree(namesAndObjects);
    }

    private static Map<String,JsonNode> flattenJson(JsonNode jsonObject){
        Iterator<JsonNode> jsonFields = jsonObject.elements();
        Iterator<String> jsonFieldNames = jsonObject.fieldNames();
        for(int i=0; i<jsonObject.size(); i++){
            JsonNode jsonNode = jsonFields.next();
            String jsonNodeFieldName = jsonFieldNames.next();
            rewriteJsonValues(jsonNode, jsonNodeFieldName);
        }
        return namesAndValues;
    }

    private static void rewriteJsonValues(JsonNode jsonNode, String jsonNodeFieldName){
        if(jsonNode.isObject()){
            Iterator<JsonNode> objectFields = jsonNode.elements();
            Iterator<String> objectFieldNames = jsonNode.fieldNames();
            putObjectNamesAndValues(objectFields, objectFieldNames);
        }else{
            namesAndValues.put(jsonNodeFieldName, jsonNode);
        }
    }

    private static void putObjectNamesAndValues(Iterator<JsonNode> objectFields, Iterator<String> objectFieldNames) {
        for(int i=1; i<=2; i++) {
            namesAndValues.put(objectFieldNames.next(), objectFields.next());
        }
    }
}
