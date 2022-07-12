package com.company.zad2pop;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.zad2pop.csv.CSVReader.readSelectedCSVColumn;
import static com.company.zad2pop.json.JSONToCSVConverter.JSONToCSV;

@Service
public class EndpointService {
    protected static String endpoint1(int x) throws IOException {

        JSONToCSV(x);
        List<String> selectedFields = new ArrayList<>();
        selectedFields.add("type");
        selectedFields.add("_id");
        selectedFields.add("name");
        selectedFields.add("type");
        selectedFields.add("latitude");
        selectedFields.add("longitude");
        return readSelectedCSVColumn(selectedFields);
    }

    protected static String endpoint2(int x, List<String> selectedFields) throws IOException {

        JSONToCSV(x);
        return readSelectedCSVColumn(selectedFields);
    }
}

