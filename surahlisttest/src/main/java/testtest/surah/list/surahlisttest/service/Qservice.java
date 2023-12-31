package testtest.surah.list.surahlisttest.service;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonReaderFactory;
import testtest.surah.list.surahlisttest.model.Ayah;
import testtest.surah.list.surahlisttest.model.SavedData;
import testtest.surah.list.surahlisttest.model.Surah;

@Service
public class Qservice {

    @Autowired
    private RedisTemplate<String, SavedData> redisTemplate;

    private RestTemplate restTemplate = new RestTemplate();

    String baseurl="https://api.alquran.cloud/v1/surah";

    private HashOperations<String, String, SavedData> hashOperations;

    @Autowired
    public Qservice(RedisTemplate<String, SavedData> redisTemplate) {
    this.redisTemplate = redisTemplate;
    this.hashOperations = redisTemplate.opsForHash();
}



    public ResponseEntity<?> readAllSurahs(){
    ResponseEntity<?> responseEntity = restTemplate.getForEntity(baseurl, String.class);
    String jsonString = (String) responseEntity.getBody();
        
        System.out.println("JSON Response from API: " + jsonString);
    
    return restTemplate.getForEntity(baseurl, String.class);
    }

    public ResponseEntity<String> readApiArabic(Integer number) {
        String apiUrl = String.format("%s/%s", baseurl, number);
        return restTemplate.getForEntity(apiUrl, String.class);
    }
    
    public ResponseEntity<?> readApiOtherLang(Integer number, String language ){
        String apiUrl = String.format("%s/%s/%s", baseurl, number, language);
        return restTemplate.getForEntity(apiUrl, String.class);

    }

     public ResponseEntity<?> readApiRomanized(Integer number){
        String language = "en.transliteration";
        String apiUrl = String.format("%s/%s/%s", baseurl, number, language);
        return restTemplate.getForEntity(apiUrl, String.class);

    }
    

    public List<Surah> parseSurahList(String jsonString) {
    try (JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
        JsonObject jsonObject = jsonReader.readObject();

        if (jsonObject.containsKey("data")) {
            JsonArray surahArray = jsonObject.getJsonArray("data");

            // System.out.println(surahArray);

            return surahArray
                    .stream()
                    .map(jsonValue -> parseSurahObject((JsonObject) jsonValue))
                    .collect(Collectors.toList());

          
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return Collections.emptyList();}




     private Surah parseSurahObject(JsonObject jsonSurah) {
        Surah surah = new Surah();
        surah.setNumber(jsonSurah.getInt("number", 0));
        surah.setName(jsonSurah.getString("name", ""));
        surah.setEnglishName(jsonSurah.getString("englishName", ""));
        surah.setEnglishNameTranslation(jsonSurah.getString("englishNameTranslation", ""));
        surah.setNumberOfAyahs(jsonSurah.getInt("numberOfAyahs", 0));
        surah.setRevelationType(jsonSurah.getString("revelationType", ""));
    
        return surah;
    }

     public List<Ayah> parseAyahList(String jsonString) {
     JsonReaderFactory readerFactory = Json.createReaderFactory(null);
    
        try (JsonReader jsonReader = readerFactory.createReader(new StringReader(jsonString))) {
            JsonObject jsonObject = jsonReader.readObject();
    
            // System.out.println("Parsed JSON Object: " + jsonObject);
    
            if (jsonObject.containsKey("data")) {
                JsonObject dataObject = jsonObject.getJsonObject("data");
                if (dataObject.containsKey("ayahs")) {
                    JsonArray ayahsArray = dataObject.getJsonArray("ayahs");
    
                    List<Ayah> ayahList = new ArrayList<>();
    
                    for (jakarta.json.JsonValue jsonValue : ayahsArray) {
                        JsonObject jsonAyah = (JsonObject) jsonValue;
                        Ayah ayah = parseAyahObject(jsonAyah);
                        ayahList.add(ayah);
                    }
    
                    return ayahList;
                } else {
                    System.out.println("Key 'ayahs' is missing in the JSON response.");
                }
            } else {
                System.out.println("Key 'data' is missing in the JSON response.");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    
        return Collections.emptyList(); 
    }
    

  

private Ayah parseAyahObject(JsonObject jsonAyah) {
    Ayah ayah = new Ayah();
    ayah.setNumber(jsonAyah.getInt("number", 0));
    ayah.setText(jsonAyah.getString("text", ""));
    ayah.setNumberInSurah(jsonAyah.getInt("numberInSurah", 0));
    ayah.setJuz(jsonAyah.getInt("number", 0));
    ayah.setManzil(jsonAyah.getInt("manzil", 0));
    ayah.setPage(jsonAyah.getInt("Page", 0));
    ayah.setRuku(jsonAyah.getInt("Ruku", 0));
    ayah.setHizbQuarter(jsonAyah.getInt("HizbQuarter", 0));
    ayah.setSajda(jsonAyah.getBoolean("sajda", false));
   

    return ayah;
}

public void saveDataToRedis(SavedData savedData) {
    String redisKey = savedData.getEmail();

    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("birthdate", savedData.getBirthdate());
    dataMap.put("comments", savedData.getComments());
    dataMap.put("selectedSurahEnglishName", savedData.getSelectedSurahEnglishName());  

    redisTemplate.opsForHash().putAll(redisKey, dataMap);
}

public SavedData loadDataFromRedis(String email) {
    String redisKey = email;

    Map<Object, Object> dataMap = redisTemplate.opsForHash().entries(redisKey);

    SavedData savedData = new SavedData(
        (String) dataMap.get("birthdate"),
        (String) dataMap.get("comments"),
        email, 
        (String) dataMap.get("selectedSurahEnglishName")
    );

    return savedData;
}


public boolean existsInRedis(String email) {
    return redisTemplate.hasKey(email);
}





}



