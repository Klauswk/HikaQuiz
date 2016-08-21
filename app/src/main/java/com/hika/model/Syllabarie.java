package com.hika.model;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public class Syllabarie {

    public int id;
    public String syllabary;
    public String translation;
    public SyllabaryType syllabaryType;

    public Syllabarie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSyllabary() {
        return syllabary;
    }

    public void setSyllabary(String syllabary) {
        this.syllabary = syllabary;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public SyllabaryType getSyllabaryType() {
        return syllabaryType;
    }

    public void setSyllabaryType(SyllabaryType syllabaryType) {
        this.syllabaryType = syllabaryType;
    }

    @Override
    public String toString() {
        return syllabary;
    }
}
