package testtest.surah.list.surahlisttest.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;

public class SavedData {
    

    
    @NotBlank(message = "Date cannot be blank")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String currentdate;

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

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFormattedCurrentdate() {
        if (currentdate != null && !currentdate.isEmpty()) {
            try {
               
                SimpleDateFormat currentFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = currentFormat.parse(currentdate);

                
                SimpleDateFormat desiredFormat = new SimpleDateFormat("dd-MM-yyyy");
                return desiredFormat.format(date);
            } catch (ParseException e) {
               
                e.printStackTrace();
            }
        }
        return ""; 
    }

    public SavedData(String currentdate, String comments, String email, String selectedSurahEnglishName) {
        this.currentdate = currentdate;
        this.comments = comments;
        this.email = email;
        this.selectedSurahEnglishName = selectedSurahEnglishName;
    }
    

    @Override
    public String toString() {
        return "Loaded Data{" +
                "Date=" + currentdate +
                ", comments =" + comments  +
                ", Surah Name = "  + selectedSurahEnglishName +
                '}';

    }


}




