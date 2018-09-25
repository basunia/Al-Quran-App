package com.lab.bitech.greentech.quranapp.models;

public class SurahDetailModel {

    private String ayaatName;
    private String translation;

    public SurahDetailModel(String ayaatName, String translation) {
        this.ayaatName = ayaatName;
        this.translation = translation;
    }

    public String getAyaatName() {
        return ayaatName;
    }

    public void setAyaatName(String ayaatName) {
        this.ayaatName = ayaatName;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
