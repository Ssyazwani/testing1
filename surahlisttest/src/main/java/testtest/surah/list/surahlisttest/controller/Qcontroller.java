package testtest.surah.list.surahlisttest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import testtest.surah.list.surahlisttest.model.Ayah;
import testtest.surah.list.surahlisttest.model.SavedData;
import testtest.surah.list.surahlisttest.model.Surah;
import testtest.surah.list.surahlisttest.service.Qservice;

@Controller
public class Qcontroller{

    @Autowired
    Qservice qService;

    
@GetMapping("/surahList")
public String showSurahList(Model model) {
    ResponseEntity<?> response = qService.readAllSurahs();
    List<Surah> surahList = qService.parseSurahList((String) response.getBody());

    Surah selectedSurah = new Surah();
    model.addAttribute("surahList", surahList);
    model.addAttribute("selectedSurah", selectedSurah);
    System.out.println(surahList);

    return "surahList";
}


@PostMapping("/surahList")
public String processSurahSelection(@ModelAttribute Surah selectedSurah,
                                     @RequestParam String action,
                                     @RequestParam String language,
                                     Model model
                                     ) {

    Integer surahNumber = selectedSurah.getNumber();
    System.out.println(surahNumber);

    ResponseEntity<?> response = qService.readAllSurahs();

    String responseBody = (String) response.getBody();
    List<Surah> surahList = qService.parseSurahList(responseBody);

    System.out.println("Selected Surah Number: " + surahNumber);

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

 



// @PostMapping("/save")
// public String saveData(@RequestParam String email,
//                        @RequestParam String comments,
//                        Model model) {

//     qService.saveDataToRedis(email, comments);

//     return "redirect:/viewSaved?email=" + email;
// }

// @GetMapping("/viewSaved")
// public String viewSavedData(@RequestParam String email, Model model) {
//     List<String> comments = qService.loadCommentsFromRedis(email);
//     model.addAttribute("comments", comments);
//     return "viewSaved";


@GetMapping("/userPage")
public String showUserPage() {
    return "userPage";
}

@PostMapping("/save")
public String saveData(@RequestParam String email,
                       @RequestParam String birthdate,
                       @RequestParam String comments,
                       Model model) {

    qService.saveDataToRedis(email, birthdate, comments);

    return "redirect:/viewSaved?email=" + email;
}

@GetMapping("/viewSaved")
public String viewSaved(String email, Model model) {
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



// @PostMapping("/search")
// public String findData(@RequestParam String email, Model model) {
//     if (qService.existsInRedis(email)) {
//         SavedData savedData = qService.loadDataFromRedis(email);
//         model.addAttribute("email", email);
//         model.addAttribute("savedData", savedData);
//     } else {
//         model.addAttribute("email", email);
//     }
//     return "viewSaved";
// }



    
    // @PostMapping("/delete")
    // public String deleteSavedData(@RequestParam String email) {
    //     qService.deleteSavedData(email);
    //     return "redirect:/viewSaved";
    // }



    // @PostMapping("/delete")
    // public String deleteSavedData(@RequestParam Long id) {
    //     // Fetch the SavedData by its unique identifier
    //     SavedData savedData = qService.getSavedDataById(id);

    //     // Check if the SavedData exists
    //     if (savedData != null) {
    //         // Delete the SavedData
    //         savedData.delete();
    //         // Alternatively, you can call a method in QService to delete data
    //         // qService.deleteSavedData(savedData);

    //         // Redirect to the viewSaved page
    //         return "redirect:/viewSaved";
    //     } else {
    //         // Redirect to an error page or handle the error in some way
    //         return "errorPage";
    //     }
    // }


}






