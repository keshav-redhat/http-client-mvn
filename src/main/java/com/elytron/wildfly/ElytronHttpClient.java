package com.elytron.wildfly;

import com.elytron.wildfly.hpi.ClientConfigProvider;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Iterator;
import java.util.ServiceLoader;

public class ElytronHttpClient {

    static final String uri = "http://localhost:8080/servlet-security/SecuredServlet";
    public static void main(String[] args) throws Exception {
        Iterator<ClientConfigProvider> serviceLoaderIterator = ServiceLoader.load(ClientConfigProvider.class).iterator();
        ClientConfigProvider clientConfigProvider = serviceLoaderIterator.next();
        String username = clientConfigProvider.getUsername(new URI(uri));
        String password = clientConfigProvider.getPassword(new URI(uri));
        System.out.println(password);
        System.out.println(username);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(uri))
                .header("Authorization",basicAuth("",""))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.headers());
    }
    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
    public static void connect(String uri){

    }
}
