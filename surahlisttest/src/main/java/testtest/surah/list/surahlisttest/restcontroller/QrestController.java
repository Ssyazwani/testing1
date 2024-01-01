package testtest.surah.list.surahlisttest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import testtest.surah.list.surahlisttest.model.Surah;
import testtest.surah.list.surahlisttest.service.Qservice;

@RestController
@RequestMapping("/api")
public class QrestController {

    @Autowired
    private Qservice qService;

    @GetMapping("/surahList")
    public ResponseEntity<List<Surah>> getSurahList(HttpSession session) {
        ResponseEntity<?> response = qService.readAllSurahs();
        List<Surah> surahList = qService.parseSurahList((String) response.getBody());

        session.setAttribute("surahList", surahList);

        return ResponseEntity.ok(surahList);
    }

    @GetMapping("/arabic/{number}")
    public ResponseEntity<String> readApiArabic(@PathVariable Integer number) {
        return qService.readApiArabic(number);
    }

    @GetMapping("/otherLang/{number}/{language}")
    public ResponseEntity<?> readApiOtherLang(@PathVariable Integer number, @PathVariable String language) {
        return qService.readApiOtherLang(number, language);
    }

    @GetMapping("/romanized/{number}")
    public ResponseEntity<?> readApiRomanized(@PathVariable Integer number) {
        return qService.readApiRomanized(number);
    }
}

    
   



   


