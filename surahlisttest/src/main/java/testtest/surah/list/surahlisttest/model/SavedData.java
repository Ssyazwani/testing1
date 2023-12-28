package testtest.surah.list.surahlisttest.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;

public class SavedData {
    

    
    @NotBlank(message = "Comments cannot be blank")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String birthdate;

    @NotBlank(message = "Comments cannot be blank")
    private String comments;

    @NotBlank(message = "Comments cannot be blank")
    private String email;


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

    public SavedData(String birthdate, String comments) {
        this.birthdate = birthdate;
        this.comments = comments;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Loaded Data{" +
                "Date=" + birthdate +
                ", comments ='" + comments  +
                '}';

    }



}
