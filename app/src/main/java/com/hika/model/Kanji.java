package com.hika.model;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public class Kanji extends Syllabarie {

    private int grade;

    public Kanji(){
        setSyllabaryType(SyllabaryType.KANJI);
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
