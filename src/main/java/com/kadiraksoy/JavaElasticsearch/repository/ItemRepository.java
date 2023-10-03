package com.kadiraksoy.JavaElasticsearch.repository;

import com.kadiraksoy.JavaElasticsearch.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<Item, String > {
}
