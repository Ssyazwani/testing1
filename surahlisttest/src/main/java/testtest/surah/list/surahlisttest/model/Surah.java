package testtest.surah.list.surahlisttest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Surah implements Serializable {

    private Integer number;
    private String name;
    private String englishName;
    private String englishNameTranslation;
    private Integer numberOfAyahs;
    private String revelationType;

    

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEnglishName() {
        return englishName;
    }
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
    public String getEnglishNameTranslation() {
        return englishNameTranslation;
    }
    public void setEnglishNameTranslation(String englishNameTranslation) {
        this.englishNameTranslation = englishNameTranslation;
    }
    public Integer getNumberOfAyahs() {
        return numberOfAyahs;
    }
    public void setNumberOfAyahs(Integer numberOfAyahs) {
        this.numberOfAyahs = numberOfAyahs;
    }
    public String getRevelationType() {
        return revelationType;
    }
    public void setRevelationType(String revelationType) {
        this.revelationType = revelationType;
    }



    @Override
    public String toString() {
        return "Surah{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", englishNameTranslation='" + englishNameTranslation + '\'' +
                ", numberOfAyahs=" + numberOfAyahs +
                ", revelationType='" + revelationType + '\'' +
                '}';
    }



    
}