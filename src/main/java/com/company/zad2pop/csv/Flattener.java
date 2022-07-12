package com.company.zad2pop.csv;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class Flattener {

    public static JsonNode flatten(JsonNode jsonNode) {
        List<Map<String, JsonNode>> maps = new ArrayList<Map<String, JsonNode>>();
        Iterator<JsonNode> elements = jsonNode.elements();
        for (int i = 0; i < jsonNode.size(); i++) {
            JsonNode tmp = elements.next();
            Iterator<JsonNode> fields = tmp.elements();
            Iterator<String> fieldnames = tmp.fieldNames();
            Map<String, JsonNode> map = new HashMap<>();
            for (int j = 0; j < tmp.size(); j++) {
                for (int k = 0; k < tmp.size(); k++) {
                    JsonNode tmp2 = fields.next();
                    String fieldname = fieldnames.next();
                    if (tmp2.isObject()) {
                        Iterator<JsonNode> objectFields = tmp2.elements();
                        Iterator<String> objectFieldnames = tmp2.fieldNames();
                        map.put(objectFieldnames.next(), objectFields.next());
                        map.put(objectFieldnames.next(), objectFields.next());
                    } else {
                        map.put(fieldname, tmp2);
                    }
                }
                maps.add(map);
            }
            ObjectMapper mapper = new ObjectMapper();

        }
        return jsonNode;
    }

}
