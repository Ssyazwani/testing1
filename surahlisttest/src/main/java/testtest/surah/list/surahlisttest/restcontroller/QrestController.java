package testtest.surah.list.surahlisttest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import testtest.surah.list.surahlisttest.model.Ayah;
import testtest.surah.list.surahlisttest.model.Surah;
import testtest.surah.list.surahlisttest.service.Qservice;

@RestController
@RequestMapping("/api")
public class QrestController {

    @Autowired
    Qservice qService;
    

     @GetMapping("/allSurahs")
    public ResponseEntity<List<Surah>> readAllSurahs() {
        ResponseEntity<?> responseEntity = qService.readAllSurahs();
        String jsonString = responseEntity.getBody().toString();
        return ResponseEntity.ok(qService.parseSurahList(jsonString));
    }

   

    @GetMapping("/arabic")
    public ResponseEntity<List<Ayah>> readApiArabic(@RequestParam Integer number) {
        return fetchDataAndParse(number);
    }

    @GetMapping("/otherLang")
    public ResponseEntity<List<Ayah>> readApiOtherLang(@RequestParam Integer number, @RequestParam String language) {
        return fetchDataAndParse(number, language);
    }

    private ResponseEntity<List<Ayah>> fetchDataAndParse(Integer number) {
        ResponseEntity<?> responseEntity = qService.readApiArabic(number);
        String jsonString = responseEntity.getBody().toString();
        return ResponseEntity.ok(qService.parseAyahList(jsonString));
    }

    private ResponseEntity<List<Ayah>> fetchDataAndParse(Integer number, String language) {
        ResponseEntity<?> responseEntity = qService.readApiOtherLang(number, language);
        String jsonString = responseEntity.getBody().toString();
        return ResponseEntity.ok(qService.parseAyahList(jsonString));
    }
}
