package me.wgoddard.utilities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPThing {

    private static final String POSTS_API_URL = "http://localhost:8080/api/";

    private static final HttpClient client = HttpClient.newHttpClient();

    public static String postRequest(String mapping, String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .uri(URI.create(POSTS_API_URL + mapping))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String getRequest(String mapping) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL + mapping))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String putRequest(String mapping, String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .uri(URI.create(POSTS_API_URL + mapping))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String deleteRequest(String mapping) {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL + mapping))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
