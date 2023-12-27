package testtest.surah.list.surahlisttest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import testtest.surah.list.surahlisttest.model.Ayah;
import testtest.surah.list.surahlisttest.model.SavedData;
import testtest.surah.list.surahlisttest.model.Surah;
import testtest.surah.list.surahlisttest.service.Qservice;

@Controller
public class Qcontroller{

    @Autowired
    Qservice qService;

    
@GetMapping("/surahList")
public String showSurahList(Model model,HttpSession session) {
    ResponseEntity<?> response = qService.readAllSurahs();
    List<Surah> surahList = qService.parseSurahList((String) response.getBody());

    Surah selectedSurah = new Surah();
    session.setAttribute("surahList", surahList);
    session.setAttribute("selectedSurah", selectedSurah);
    model.addAttribute("surahList", surahList);
    model.addAttribute("selectedSurah", selectedSurah);
    System.out.println(surahList);

    return "surahList";
}


@PostMapping("/surahList")
public String processSurahSelection(@ModelAttribute Surah selectedSurah,
                                     @RequestParam String action,
                                     @RequestParam String language,
                                     Model model,
                                     HttpSession session
                                     ) {

    Integer surahNumber = selectedSurah.getNumber();
    System.out.println(surahNumber);

    ResponseEntity<?> response = qService.readAllSurahs();

    String responseBody = (String) response.getBody();
    // List<Surah> surahList = qService.parseSurahList(responseBody);

    System.out.println("Selected Surah Number: " + surahNumber);
    List<Surah> surahList = (List<Surah>) session.getAttribute("surahList");
    Surah detailedSurah = surahList.stream()
            .filter(surah -> surah.getNumber().equals(surahNumber))
            .findFirst()
            .orElse(null);

    System.out.println("Detailed Surah: " + detailedSurah);

    model.addAttribute("selectedSurah", detailedSurah);

    if ("viewBookmarked".equals(action)) {
        return "emailPage";

    } else if ("find".equals(action)) {

        ResponseEntity<?> responseOtherLang = qService.readApiOtherLang(surahNumber, language);

        List<Ayah> ListOtherLang = qService.parseAyahList(responseOtherLang.getBody().toString());
       
        ResponseEntity<String> responseArabic = qService.readApiArabic(surahNumber);
        List<Ayah> ListArabic = qService.parseAyahList(responseArabic.getBody());


        model.addAttribute("ListOtherLang", ListOtherLang);
        model.addAttribute("ListArabic", ListArabic);

        return "surahPage";
    }

    return "surahPage";
}

    @GetMapping("/goback")
    public String handleGoBackAction() {
       return "redirect:/surahList"; 
   }



@GetMapping("/userPage")
public String showUserPage(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession();
    return "userPage";
}



@PostMapping("/save")
public String saveData(@RequestParam String email,
                       @RequestParam String birthdate,
                       @RequestParam String comments,
                       Model model,HttpSession session) {

    qService.saveDataToRedis(email, birthdate, comments);

    return "redirect:/viewSaved/" + email;
}

@GetMapping("/viewSaved/{email}")
public String viewSaved(@PathVariable String email, Model model) {
    SavedData savedData = qService.loadDataFromRedis(email);
    model.addAttribute("email", email);
    model.addAttribute("savedData", savedData);
    return "viewSaved";  
}


@PostMapping("/search")
public String findData(@RequestParam String email, Model model) {
    String redisKey =  email;

    if (qService.existsInRedis(redisKey)) {
        SavedData savedData = qService.loadDataFromRedis(redisKey);
        System.out.println("Loaded Data: " + savedData); 
        model.addAttribute("email", email);
        model.addAttribute("savedData", savedData);
    } else {
        model.addAttribute("email", email);
    }
    return "viewSaved";
}




}






