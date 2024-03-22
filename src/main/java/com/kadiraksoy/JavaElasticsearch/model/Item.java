package com.kadiraksoy.JavaElasticsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "item_index")
@Getter
@Setter
@Setting(settingPath = "static/es-settings.json")//Kuralları belirtiyoruz denebilir
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    private int id;

    @Field(name = "name", type = FieldType.Text, analyzer = "custom_index", searchAnalyzer = "custom_search")
    private String name;

    @Field(name = "price", type = FieldType.Double)
    private Double price;

    @Field(name = "brand", type = FieldType.Text, analyzer = "custom_index", searchAnalyzer = "custom_search")
    private String brand;

    @Field(name = "category", type = FieldType.Keyword)
    private String category;

}
