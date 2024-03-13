package com.example.awslogservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceInvoker {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static String[] serviceURLs = null;
    private static int instance = 0;

    public ServiceInvoker(String[] urls) {
        serviceURLs = urls;
    }

    public static String invoke(String msg) throws IOException {
        URL url = new URL(serviceURLs[instance] + msg);
        if (instance == serviceURLs.length - 1) {
            instance = 0;
        } else {
            instance++;
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = 0;
        System.out.println("GET Response Code :: " + responseCode);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        responseCode = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println(response);
        System.out.println("GET DONE");
        return response.toString();
    }
}
