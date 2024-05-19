## Elasticsearch Nedir?
Elasticsearch, veri arama, analiz ve gorsellestirme islemlerini hizli ve etkili bir sekilde gerceklestirmenizi saglayan bir acik kaynakli veri arama ve analiz platformudur. 
Elasticsearch, buyuk miktardaki verileri duzenli bir sekilde saklayabilen, hizli ve olceklenebilir bir veritabani ve arama motoru(**full-text search**) olarak dusunulebilir.


## Elasticsearch Sorgu Tipleri

<details>
<summary>Match Query</summary>
Belirli bir metin terimini veya sorgu ifadesini arar ve eslesen belgeleri dondurur.
</details>

<details>
<summary>Term Query</summary>
Belirli bir alanin degerinin tam eslesme icin kullanilir.
</details>

<details>
<summary>Bool Query</summary>
Mantiksal operatorleri (AND, OR, NOT) kullanarak birden cok sorguyu birlestirmek icin kullanilir.
</details>

<details>
<summary>Range Query</summary>
Bir alanin belirli bir araliga dusup dusmedigini kontrol eder.
</details>

<details>
<summary>Fuzzy Query</summary>
Benzer ancak kesin olmayan terimleri aramak icin kullanilir.
</details>

<details>
<summary>Wildcard Query</summary>
Jokert karakterler (* veya ?) kullanarak eslesen terimleri bulmak icin kullanilir.
</details>

<details>
<summary>Prefix Query</summary>
Belirli bir on ek ile baslayan terimleri aramak icin kullanilir.
</details>

<details>
<summary>Autocomplete Suggester</summary>
Otomatik tamamlama islevselligi icin kullanilir ve kullanicinin yazmaya basladigi terimleri tamamlamak icin kullanilir.
</details>

<details>
<summary>Highlighting</summary>
Eslesen terimleri veya metni vurgulamak icin kullanilir.
</details>

<details>
<summary>Aggregations</summary>
Verileri toplamak, gruplamak ve analiz etmek icin kullanilir.
</details>

<details>
<summary>Ranking Functions</summary>
Elasticsearch'in belgeleri siralamak icin kullanabileceginiz cesitli siralama islevleri vardir.
</details>

<details>
<summary>Match Phrase Query</summary>
Metin teriminin tamamini iceren belgeleri dondurmek icin kullanilir.
</details>



## Endpoints ve Aciklamalari
<table style="width:70%">
  <tr>
      <th>Method</th>
      <th>Method Name</th>
      <th>Url</th>
      <th>Description</th>
      <th>Request Body</th>
  </tr>
  <tr>
      <td>POST</td>
      <td>createIndex</td>
      <td>/api/v1/items</td>
      <td>Elasticsearch veritabanina veri eklemeyi saglar.</td>
      <td>Item<br>
      {
        "id": "99",
        "name": "test",
        "price": 1,
        "brand": "test",
        "category": "test"
      }</td>
  <tr>
  <tr>
      <td>POST</td>
      <td>addItemsFromJson</td>
      <td>/api/v1/items/init-index</td>
      <td>JSON'daki verileri alip Elasticsearch veritabanina kayit eder.</td>
      <td></td>
  <tr>
  <tr>
      <td>GET</td>
      <td>findAll</td>
      <td>/api/v1/items/findAll</td>
      <td>Elasticsearch veritabanina kaydedilmis tum verileri getirir.</td>
      <td></td>
  <tr>
  <tr>
      <td>GET</td>
      <td>getAllItemsFromAllIndexes</td>
      <td>/api/v1/items/allIndexes</td>
      <td>Elasticsearch veritabani icindeki tum indekslerdeki verileri getirir.</td>
      <td></td>
  <tr>
  <tr>
      <td>GET</td>
      <td>getAllDataFromIndex</td>
      <td>/api/v1/items/getAllDataFromIndex/{indexName}</td>
      <td>Elasticsearch veritabani icindeki istenilen indekslerdeki verileri getirir.</td>
      <td></td>
  <tr>
  <tr>
      <td>GET</td>
      <td>searchItemsByFieldAndValue</td>
      <td>/api/v1/items/search</td>
      <td>Elasticsearch veritabaninda istenilen alanda istenilen veriyi arar.</td>
      <td>SearchRequestDto<br>
      {
        "fieldName": [
            "name"
        ],
        "searchValue": [
            "Ultimate"
        ]
      }
      </td>
  <tr>
  <tr>
      <td>GET</td>
      <td>searchItemsByNameAndBrandWithQuery</td>
      <td>/api/v1/items/search/{name}/{brand}</td>
      <td>Elasticsearch veritabaninda isme ve markaya gore arama yapar.</td>
      <td></td>
  <tr>
  <tr>
      <td>GET</td>
      <td>boolQuery</td>
      <td>/api/v1/items/boolQuery</td>
      <td>Elasticsearch veritabaninda istenilen alanda istenilen veriyi bool query olarak arar.</td>
      <td>SearchRequestDto<br>
      {
        "fieldName": [
            "name",
            "brand"
        ],
        "searchValue": [
            "mega",
            "xyz"
        ]
      }
      </td>
  <tr>
  <tr>
      <td>GET</td>
      <td>autoSuggestItemsByName</td>
      <td>/api/v1/items/autoSuggest/{name}</td>
      <td>Bir urun adi alir ve Elasticsearch veritabaninda bu ada benzer urun adlarini bulup dondurur.</td>
      <td></td>
  <tr>
  <tr>
      <td>GET</td>
      <td>autoSuggestItemsByNameWithQuery</td>
      <td>/api/v1/items/suggestionsQuery/{name}</td>
      <td>Bir urun adi alir ve Elasticsearch veritabaninda bu ada benzer urun adlarini bulup dondurur.</td>
      <td></td>
  <tr>
</table>

### searchItemsByNameAndBrandWithQuery'nin custom query'sinin aciklamasi:
```java
@Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"brand\": \"?1\"}}]}}")
List<Item> searchItemsByNameAndBrandWithQuery(String name, String brand);
```
- **bool:** Bu, Elasticsearch sorgusunun bir "bool" (boolean) sorgusu oldugunu belirtir. Bool sorgusu, baska sorgulari bir araya getirme ve mantiksal islemler yapma yetenegi saglar.

- **must:** Bool sorgusunun icinde yer alan "must" bolumu, bu sorgunun icermesi gereken kosullari belirtir. Yani, bu sorgu, hem adi hem de markayi icermesi gereken belgeleri arar.

- **match:** Bu, bir alanin (field) icerigini belirtilen bir degerle eslestirmek icin kullanilan sorgu turunu belirtir.

- **name ve brand:** Bu, her bir "match" sorgusunun eslesecegi alanlarin adini belirtir. Yani, "name" alani belirtilen name degeri ile eslesmeli ve "brand" alani belirtilen brand degeri ile eslesmelidir.

- **"?0" ve "?1":** Bu, "match" sorgularinin eslesecegi degerleri temsil eder. ?0 ve ?1 ifadeleri, bu sorgunun metodun parametrelerine sirasiyla name ve brand degerlerini alacagini gosterir. Yani, bu parametreler ile belirtilen degerlerle eslesmeyi arar.



## Tech Stack

- Java 17
- Spring Boot 3.0
- Spring Elasticsearch Data
- Docker
- Lombok


## Build & Run

```
  docker-compose -f docker-compose.yml up -d
```

```
  mvn clean install && mvn --projects backend spring-boot:run
```
