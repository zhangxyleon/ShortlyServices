package com.internProject.shortly;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /shorten success")
    void testPostShortenSuccess() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> body = new HashMap<>();
        body.put("longUrl", "https://stackoverflow.com/questions/65939134/how-to-use-correctly-mockmvc-to-test-post-method-in-junit");
        body.put("encodeMethod", "base62");

        MvcResult authResult = null;
        authResult = mockMvc.perform(post("/shorten").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(body)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String shortUrl = authResult.getResponse().getContentAsString();

        System.out.println("POST /shorten success");
    }
    @Test
    @DisplayName("POST /shorten inValid URl")
    void testPostShortenInvalidUrl() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> body = new HashMap<>();
        body.put("longUrl", "asdasd");
        body.put("encodeMethod", "base62");


        mockMvc.perform(post("/shorten").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(body)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        System.out.println("POST /shorten inValid URl");

    }

    @Test
    @DisplayName("POST /shorten inValid encoding method")
    void testPostShortenWrongEncodingMethod() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> body = new HashMap<>();
        body.put("longUrl", "https://stackoverflow.com/questions/65939134/how-to-use-correctly-mockmvc-to-test-post-method-in-junit");
        body.put("encodeMethod", "asdfasd");


        mockMvc.perform(post("/shorten").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(body)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        System.out.println("POST /shorten inValid encoding method");

    }


    @Test
    @DisplayName("GET /{shortUrl} success")
    void testGetOriginUrlSuccess() throws Exception {
        String longUrl = "https://stackoverflow.com/questions/65939134/how-to-use-correctly-mockmvc-to-test-post-method-in-junit";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> body = new HashMap<>();
        body.put("longUrl", longUrl);
        body.put("encodeMethod", "base62");

        MvcResult authResult = null;
        authResult = mockMvc.perform(post("/shorten").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(body)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String shortUrl = authResult.getResponse().getContentAsString();
        System.out.println(shortUrl);
        authResult =   authResult = mockMvc.perform(get("/{shortUrl}", shortUrl.substring(shortUrl.length() - 6)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        System.out.println(authResult.getResponse().getContentAsString());
        Assertions.assertEquals(longUrl, authResult.getResponse().getContentAsString());
        System.out.println("GET /{shortUrl} success");
    }


    @Test
    @DisplayName("GET /{shortUrl} original Url not exist")
    void testGetOriginUrlFail() throws Exception {

        String shortUrl = "http://localhost:8080/1zhxL3";
        mockMvc.perform(get("/{shortUrl}", shortUrl.substring(shortUrl.length() - 6)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        System.out.println("GET /{shortUrl} original Url not exist");
    }



}
