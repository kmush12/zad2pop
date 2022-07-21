package com.company.zad2pop.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class CSVReader {

    public String readSelectedCSVColumn(List<String> headerlist) {

        try (BufferedReader br = new BufferedReader(FilePath());
             CSVParser parser = CSVFormat.DEFAULT.builder().setHeader().setDelimiter(',').build().parse(br)) {

                return readDataByHeaders(headerlist, parser);

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private String readDataByHeaders(List<String> headerlist, CSVParser parser) {
        StringBuilder string = new StringBuilder();
        for (String header : headerlist) {
            if (!objectComparator(header, headerlist)) {
                string.append(header).append(", ");
            } else {
                string.append(header).append("\n");
            }
        }
        for (CSVRecord record : parser) {
            string.append(readRecord(headerlist, record));
        }
        return string.toString();
    }

    private String readRecord(List<String> headerlist, CSVRecord record) {
        StringBuilder string = new StringBuilder();
        
            for (String header : headerlist) {
                if (!objectComparator(header, headerlist)) {
                    string.append(record.get(header)).append(",");
                } else {
                    string.append(record.get(header)).append("\n");
                }
            }
        return string.toString();
        }
        
    

    private FileReader FilePath() throws FileNotFoundException {
       return new FileReader("src/main/resources//dane.csv");
    }

    private boolean objectComparator(String header, List<String> headerlist) {
        return (Objects.equals(header, previousHeader(headerlist)));
    }

    private String previousHeader(List<String> headerlist) {
        return headerlist.get(previousHeaderNumber(headerlist));
    }

    private int previousHeaderNumber(List<String> headerlist) {
        return headerlist.size() - 1;
    }
}

