package testtest.surah.list.surahlisttest.model;


import java.io.Serializable;
import java.util.List;

public class Ayah implements Serializable{

    private Integer number;
    private String text;
    public  Integer numberInSurah;
    private Integer juz;
    private Integer manzil;
    private Integer page;
    private Integer ruku;
    private Integer hizbQuarter;
    private boolean sajda;
    private String filePath;
    
    
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumberInSurah() {
        return numberInSurah;
    }
    public void setNumberInSurah(Integer numberInSurah) {
        this.numberInSurah = numberInSurah;
    }
    public Integer getJuz() {
        return juz;
    }
    public void setJuz(Integer juz) {
        this.juz = juz;
    }
    public Integer getManzil() {
        return manzil;
    }
    public void setManzil(Integer manzil) {
        this.manzil = manzil;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getRuku() {
        return ruku;
    }
    public void setRuku(Integer ruku) {
        this.ruku = ruku;
    }
    public Integer getHizbQuarter() {
        return hizbQuarter;
    }
    public void setHizbQuarter(Integer hizbQuarter) {
        this.hizbQuarter = hizbQuarter;
    }
    public boolean isSajda() {
        return sajda;
    }
    public void setSajda(boolean sajda) {
        this.sajda = sajda;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    

    @Override
    public String toString() {
        return "Ayah{" +
                "number=" + number +
                ", text='" + text + '\'' +
                ", numberInSurah=" + numberInSurah + 
                "juz =" + juz +
                ", manzil='" + manzil + '\'' +
                "page =" + page +
                ", ruku ='" + ruku + '\'' +
                 "hizQuarter=" + hizbQuarter +
                ", sajda='" + sajda + '\'' +
                '}';



    
}


}
