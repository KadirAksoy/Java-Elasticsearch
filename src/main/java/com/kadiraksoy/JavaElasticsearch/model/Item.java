package com.kadiraksoy.JavaElasticsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "item_index")
@Getter
@Setter
@Setting(settingPath = "static/es-settings.json")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "price")
    private String price;
    @Field(name = "brand")
    private String brand;
    @Field(name = "category")
    private String category;

}
