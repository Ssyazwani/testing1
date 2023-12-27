package testtest.surah.list.surahlisttest.model;


public class SavedData {
    
   
    private String birthdate;


    private String comments;


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
    }

    @Override
    public String toString() {
        return "Loaded Data{" +
                "Date=" + birthdate +
                ", comments ='" + comments  +
                '}';

    }



}
