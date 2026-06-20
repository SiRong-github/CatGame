package com.catgame;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;

public class CatGame {
    private static final int FACTS_NUM = 3;
    private static final String API_URL = "https://aphorismcookie.herokuapp.com";
    private static Gson gson = new Gson();
    private static FortuneData fortune;
    private static ArrayList<String> fortunes = new ArrayList<>();

    public static void main(String[] args) throws URISyntaxException, IOException {
        URL url = new URI(API_URL).toURL();
        for (int i = 0; i < FACTS_NUM; i++) {
            fortunes.add(getFortune(url));
            System.out.println(fortunes.get(i));
        }


    }

    private static String getFortune(URL url) {
        try {
            HttpsURLConnection connection = fetchFortuneAPI(url);
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);
            if (responseCode != HttpsURLConnection.HTTP_ACCEPTED && responseCode != HttpsURLConnection.HTTP_OK) {
                throw new RuntimeException("Http Response is " + responseCode);
            }
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
            System.out.println(sb);
            fortune = gson.fromJson(sb.toString(), FortuneData.class);
            return fortune.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static HttpsURLConnection fetchFortuneAPI(URL url) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
