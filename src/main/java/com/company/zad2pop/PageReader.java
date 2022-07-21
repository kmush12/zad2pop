package com.company.zad2pop;

import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PageReader {

    private BufferedReader reader;
    private final StringBuilder responseContent = new StringBuilder();

    public JSONArray getPageContent(int size) throws IOException {
        setConnection(size);
        try {
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
       // System.out.println("   KUPA   " +responseContent);
        return new JSONArray(responseContent.toString());
    }

    private void setConnection(int size) throws IOException {
        URL url = new URL("http://localhost:8082/generate/json/" + size);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
    }

    private void connectionCorrectly() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        read();
    }

    private void connectionError() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        read();
    }

    private int connectionResponseCode() throws IOException {
            return connection.getResponseCode();
    }

    private void read() throws IOException {
        String line;

        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
           // System.out.println(line);
        }
        reader.close();
    }

    private HttpURLConnection connection;
}
