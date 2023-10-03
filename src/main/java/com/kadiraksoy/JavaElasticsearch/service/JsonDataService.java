package com.kadiraksoy.JavaElasticsearch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadiraksoy.JavaElasticsearch.model.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonDataService {

    private final ObjectMapper objectMapper;



    public List<Item> readItemFromJson(){
        try {
            ClassPathResource resource = new ClassPathResource("data/item.json");
            InputStream inputStream = resource.getInputStream();

            return objectMapper.readValue(inputStream, new TypeReference<List<Item>>(){
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
