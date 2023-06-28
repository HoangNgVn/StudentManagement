package com.hoang.apacheclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyClient {

    public String doGet(String url) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity);
        }

        return null;
    }

    public String doPost(String url, Object requestBody) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(requestBody);

        httpPost.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity);
        }

        return null;
    }

    public String doPut(String url, Object requestBody) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(requestBody);

        httpPut.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        httpPut.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

        HttpResponse response = httpClient.execute(httpPut);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity);
        }

        return null;
    }

    public String doDelete(String url) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);

        HttpResponse response = httpClient.execute(httpDelete);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity);
        }

        return null;
    }
}