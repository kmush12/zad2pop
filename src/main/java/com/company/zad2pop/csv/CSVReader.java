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

    private static final StringBuilder string = new StringBuilder();

    public static String readSelectedCSVColumn(List<String> headerlist) {

        try (BufferedReader br = new BufferedReader(setFileReaderPath());
             CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br)) {

                 readAllToString(headerlist, parser);

        } catch (Exception e) {
            System.out.println(e);
        }
        return string.toString();
    }
    private static void readAllToString(List<String> headerlist, CSVParser parser) {
        readHeadersLineToString(headerlist);
        readRecordsLineToString(headerlist, parser);
    }

    private static void readRecordsLineToString(List<String> headerlist, CSVParser parser) {

        for (CSVRecord record : parser) {

            for (String header : headerlist) {
                if (!objectComparator(header, headerlist)) {
                    string.append(record.get(header)).append(",");
                } else {
                    string.append(record.get(header));
                }
            }
            string.append("\n");
        }
    }

    private static void readHeadersLineToString(List<String> headerlist) {

        for (String header : headerlist) {

            if (!objectComparator(header, headerlist)) {
                string.append(header).append(", ");
            } else {
                string.append(header);
            }
        }
        string.append("\n");
    }

    private static FileReader setFileReaderPath() throws FileNotFoundException {
       return new FileReader("src/main/resources//dane.csv");
    }

    private static boolean objectComparator(String header, List<String> headerlist) {
        return (Objects.equals(header, previousHeader(headerlist)));
    }

    private static String previousHeader(List<String> headerlist) {
        return headerlist.get(previousHeaderNumber(headerlist));
    }

    private static int previousHeaderNumber(List<String> headerlist) {
        return headerlist.size() - 1;
    }
}

