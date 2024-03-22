package com.kadiraksoy.JavaElasticsearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import com.kadiraksoy.JavaElasticsearch.dto.SearchRequestDto;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class ESUtil {

    // Bu özel fonksiyon, "match_all" sorgusunu oluşturur.
    // "match_all" sorgusu, bir dizindeki tüm belgeleri döndürmek için kullanılır.
    // Yani, hiçbir filtreleme olmadan, tüm belgeleri döndürmek istediğinizde bu sorguyu kullanırsınız.
    //tüm belgeleri eşleştiren bir sorgu oluşturur.
    // Yani, herhangi bir koşula bağlı olmaksızın veritabanındaki tüm verileri döndüren bir sorgu oluşturur.
    public static Query createMatchAllQuery() {
        return Query.of(q -> q.matchAll(new MatchAllQuery.Builder().build()));
    }

    //belirli bir alan adı (field name) ve değer (value) için bir "match" sorgusunu oluşturur.
    // Bu sorgu, belirli bir alanın belirli bir değere eşleşmesini sağlar.
    // Örneğin, "title" alanındaki belgeleri belirli bir başlıkla eşleştirmek için kullanılabilir.
    // belirli bir alan adı (fieldName) ve değer (searchValue) için bir sorgu oluşturur.
    // Bu, belirli bir alanı ve belirli bir değeri eşleştirmek için kullanılabilir.
    public static Supplier<Query> buildQueryForFieldAndValue(String fieldName, String searchValue) {
        return () -> Query.of(q -> q.match(buildMatchQueryForFieldAndValue(fieldName, searchValue)));
    }

    // belirli bir alan adı ve değer için bir sorgu oluşturur
    // ve bu sorguyu bir Supplier olarak döndürür.
    // Supplier, Java'da değer sağlayan bir arayüzdür ve burada bir sorgu oluşturucuyu temsil eder.
    // Belirli bir alan adı ve değer için bir eşleştirme sorgusu oluşturan yardımcı bir metod.
    public static MatchQuery buildMatchQueryForFieldAndValue(String fieldName, String searchValue) {
        return new MatchQuery.Builder()
                .field(fieldName)
                .query(searchValue)
                .build();
    }
    //boolean sorgular oluşturmak için kullanılır.
    // SearchRequestDto nesnesinden alan adı ve değerleri alarak bir bool sorgusu oluşturur.
    // Bu metod, iki farklı koşulu (alan adı ve değerleri) birleştirmek için kullanılabilir.

    public static Supplier<Query> createBoolQuery(SearchRequestDto dto) {
        return () -> Query.of(q -> q.bool(boolQuery(dto.getFieldName().get(0).toString(), dto.getSearchValue().get(0),
                dto.getFieldName().get(1).toString(), dto.getSearchValue().get(1))));
    }
    // Belirli alan adları ve değerleri için bir boolean sorgusu oluşturan yardımcı bir metod.
    // İki koşulu da karşılayan belgeleri döndürmek için kullanılabilir.

    public static BoolQuery boolQuery(String key1, String value1, String key2, String value2) {
        return new BoolQuery.Builder()
                .filter(termQuery(key1.toString(), value1))
                .must(termQuery(key2.toString(), value2))
                .build();
    }
    // Belirli bir alan adı ve değer için bir terim sorgusu oluşturan yardımcı bir metod.
    // Tam eşleşme aramak için kullanılabilir.

    public static Query termQuery(String field, String value) {
        return Query.of(q -> q.term(new TermQuery.Builder()
                .field(field)
                .value(value)
                .build()));
    }
    //  Belirli bir alan adı ve değer için bir eşleştirme sorgusu oluşturan yardımcı bir metod.
    //  Tam eşleşme aramak için kullanılabilir.

    public static Query matchQuery(String field, String value) {
        return Query.of(q -> q.match(new MatchQuery.Builder()
                .field(field)
                .query(value)
                .build()));
    }


    // Otomatik tamamlama için bir sorgu oluşturur.
    // Verilen bir isme (name) göre arama yapmak üzere özelleştirilmiş bir analizör kullanır.
    public static Query buildAutoSuggestQuery(String name) {
        return Query.of(q -> q.match(createAutoSuggestMatchQuery(name)));
    }
    // Otomatik tamamlama için belirli bir isim için bir eşleştirme sorgusu oluşturan yardımcı bir metod.
    // Özel bir analizör kullanarak eşleşmeyi gerçekleştirir.
    public static MatchQuery createAutoSuggestMatchQuery(String name) {
        return new MatchQuery.Builder()
                .field("name")
                .query(name)
                .analyzer("custom_index")
                .build();
    }
}
