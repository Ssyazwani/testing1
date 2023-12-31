package testtest.surah.list.surahlisttest.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;

public class SavedData {
    

    
    @NotBlank(message = "Date cannot be blank")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String birthdate;

    @NotBlank(message = "Comments cannot be blank")
    private String comments;

    @NotBlank(message = "Email cannot be blank")
    private String email;
    
    @NotBlank(message = "SurahName cannot be blank")
    private String selectedSurahEnglishName;


      public String getSelectedSurahEnglishName() {
        return selectedSurahEnglishName;
    }

    public void setSelectedSurahEnglishName(String selectedSurahEnglishName) {
        this.selectedSurahEnglishName = selectedSurahEnglishName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFormattedBirthdate() {
        if (birthdate != null && !birthdate.isEmpty()) {
            try {
               
                SimpleDateFormat currentFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = currentFormat.parse(birthdate);

                
                SimpleDateFormat desiredFormat = new SimpleDateFormat("dd-MM-yyyy");
                return desiredFormat.format(date);
            } catch (ParseException e) {
               
                e.printStackTrace();
            }
        }
        return ""; 
    }

    public SavedData(String birthdate, String comments, String email, String selectedSurahEnglishName) {
        this.birthdate = birthdate;
        this.comments = comments;
        this.email = email;
        this.selectedSurahEnglishName = selectedSurahEnglishName;
    }
    

    @Override
    public String toString() {
        return "Loaded Data{" +
                "Date=" + birthdate +
                ", comments ='" + comments  +
                '}';

    }


}




