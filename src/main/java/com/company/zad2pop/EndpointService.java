package com.company.zad2pop;

import com.company.zad2pop.csv.CSVReader;
import com.company.zad2pop.json.JSONToCSVConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EndpointService {
    protected String endpoint1(int x) throws IOException {

        JSONToCSVConverter converter = new JSONToCSVConverter();
        converter.jsonToCSV(x);
        List<String> selectedFields = new ArrayList<>();
        selectedFields.add("type");
        selectedFields.add("_id");
        selectedFields.add("name");
        selectedFields.add("type");
        selectedFields.add("latitude");
        selectedFields.add("longitude");
        CSVReader csvReader = new CSVReader();
        return csvReader.readSelectedCSVColumn(selectedFields);
    }

    protected String endpoint2(int x, List<String> selectedFields) throws IOException {

        JSONToCSVConverter converter = new JSONToCSVConverter();
        converter.jsonToCSV(x);
        CSVReader csvReader = new CSVReader();
        return csvReader.readSelectedCSVColumn(selectedFields);
    }
}

