package com.hoang.apacheclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyHttpClient<T> {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public MyHttpClient(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public List<T> getAll(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String jsonResponse = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                return objectMapper.readValue(jsonResponse, new TypeReference<List<T>>() {
                });
            }
        }
        return new ArrayList<>();
    }

    public T getById(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String jsonResponse = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                return objectMapper.readValue(jsonResponse, new TypeReference<T>() {
                });
            }
        }
        return null;
    }

    public void save(String url, T data) throws IOException {
        HttpPost request = new HttpPost(url);
        String jsonPayload = objectMapper.writeValueAsString(data);
        StringEntity entity = new StringEntity(jsonPayload, StandardCharsets.UTF_8);
        entity.setContentType("application/json");
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new IOException("Failed to save data");
        }
    }

    public void update(String url, T data) throws IOException {
        HttpPut request = new HttpPut(url);
        String jsonPayload = objectMapper.writeValueAsString(data);
        StringEntity entity = new StringEntity(jsonPayload, StandardCharsets.UTF_8);
        entity.setContentType("application/json");
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new IOException("Failed to update data");
        }
    }

    public void delete(String url) throws IOException {
        HttpDelete request = new HttpDelete(url);

        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new IOException("Failed to delete data");
        }
    }
}
