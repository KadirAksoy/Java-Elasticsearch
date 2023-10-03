package com.kadiraksoy.JavaElasticsearch.controller;

import com.kadiraksoy.JavaElasticsearch.dto.SearchRequestDto;
import com.kadiraksoy.JavaElasticsearch.model.Item;
import com.kadiraksoy.JavaElasticsearch.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping()
    public Item createIndex(@RequestBody Item item) {
        return itemService.createIndex(item);
    }
    @PostMapping("/init-index")
    public void addItemsFromJson() {
        itemService.addItemsFromJson();
    }

    @GetMapping("/findAll")
    public Iterable<Item> findAll() {
        return itemService.getItems();
    }

    @GetMapping("/allIndexes")
    public List<Item> getAllItemsFromAllIndexes() {
        return itemService.getAllItemsFromAllIndexes();
    }

    @GetMapping("/getAllDataFromIndex/{indexName}")
    public List<Item> getAllDataFromIndex(@PathVariable String indexName) {
        return itemService.getAllDataFromIndex(indexName);
    }

    @GetMapping("/search")
    public List<Item> searchItemsByFieldAndValue(@RequestBody SearchRequestDto searchRequestDto) {
        return itemService.searchItemsByFieldAndValue(searchRequestDto);
    }

    @GetMapping("/search/{name}/{brand}")
    public List<Item> searchItemsByNameAndBrandWithQuery(@PathVariable String name, @PathVariable String brand) {
        return itemService.searchItemsByNameAndBrand(name, brand);
    }

    @GetMapping("/boolQuery")
    public List<Item> boolQuery(@RequestBody SearchRequestDto searchRequestDto) {
        return itemService.boolQueryFieldAndValue(searchRequestDto);
    }

    @GetMapping("/autoSuggest/{name}")
    public Set<String> autoSuggestItemsByName(@PathVariable String name) {
        return itemService.findSuggestedItemNames(name);
    }

    @GetMapping("/suggestionsQuery/{name}")
    public List<String> autoSuggestItemsByNameWithQuery(@PathVariable String name) {
        return itemService.autoSuggestItemsByNameWithQuery(name);
    }
}
