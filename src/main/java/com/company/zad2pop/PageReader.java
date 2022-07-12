package com.company.zad2pop;

import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PageReader {

    private static BufferedReader reader;
    private static final StringBuilder responseContent = new StringBuilder();

    public static JSONArray getPageContent(int size) {

        try {
            setConnection(size);
            if (connectionResponseCode() > 299) {
                connectionError();
            } else {
                connectionCorrectly();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return new JSONArray(responseContent.toString());
    }

    private static void setConnection(int size) throws IOException {
        URL url = new URL("http://localhost:8082/generate/json/" + size);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
    }

    private static void connectionCorrectly() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        read();
    }

    private static void connectionError() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        read();
    }

    private static int connectionResponseCode() throws IOException {
            return connection.getResponseCode();
    }

    private static void read() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();
    }

    private static HttpURLConnection connection;
}
