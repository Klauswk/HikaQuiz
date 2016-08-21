package com.hika.impl;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hika.dao.KanjiDAO;
import com.hika.model.Kanji;
import com.hika.model.Syllabarie;
import com.hika.model.SyllabaryType;

import java.util.ArrayList;
import java.util.List;


/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
public class KanjiDAOImpl implements KanjiDAO {

    private SQLiteDatabase database;

    public KanjiDAOImpl(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public List<Kanji> getFilteredKanjiList(String pesquisa, String mod)
    {
        Cursor cursor;
        if(mod == null){
            cursor = database.rawQuery("SELECT * FROM " + "Kanjis" + " where Answer LIKE '" + pesquisa + "%'" , null);
        }
        else{
            cursor = database.rawQuery("SELECT * FROM " + "Kanjis" + " where grade "+ mod + "'" + pesquisa + "'" , null);
        }

        ArrayList<Kanji> kanjis = new ArrayList<>();

        while(cursor.moveToNext()){
            kanjis.add(createFromCursor(cursor));
        }

        cursor.close();

        return kanjis;
    }

    @Override
    public List<Syllabarie> getFilteredHirakanaKatakanaList(SharedPreferences shared){
        Cursor cursor;
        ArrayList<Syllabarie> syllabaries = new ArrayList<>();

        String checkboxes = shared.getString("options", "");
        StringBuilder sb = new StringBuilder();
        String[] array = checkboxes.split("#");
        sb.append("key_chooser in(");
        sb.append(array[1]);
        for (int i = 2; i < array.length; i++) {
            sb.append(",");
            sb.append(array[i]);
        }
        sb.append(")");
        cursor = database.query("questionnaire", null, sb.toString(), null, null, null, null);
        while(cursor.moveToNext()){
            syllabaries.add(createFromCursor(cursor));
        }

        return syllabaries;
    }

    @Override
    public List<Syllabarie> getFilteredKanjiList(String grade){
        Cursor cursor;
        ArrayList<Syllabarie> syllabaries = new ArrayList<>();

        cursor = database.query("kanjis", null , "grade = " + String.valueOf(grade), null, null, null, null);
        while(cursor.moveToNext()){
            syllabaries.add(createFromCursor(cursor));
        }

        return syllabaries;
    }

    @Override
    public List<Syllabarie> getAnimalList(){
        Cursor cursor;
        ArrayList<Syllabarie> syllabaries = new ArrayList<>();

        cursor = database.query("animals", null , null, null, null, null, null);
        while(cursor.moveToNext()){
            syllabaries.add(createFromCursor(cursor));
        }
        return syllabaries;
    }

    @Override
    public List<Syllabarie> getClothList(){
        Cursor cursor;
        ArrayList<Syllabarie> syllabaries = new ArrayList<>();

        cursor = database.query("clothes", null , null, null, null, null, null);
        while(cursor.moveToNext()){
            syllabaries.add(createFromCursor(cursor));
        }
        return syllabaries;
    }


    @Override
    public List<Syllabarie> getHouseThingList(){
        Cursor cursor;
        ArrayList<Syllabarie> syllabaries = new ArrayList<>();

        cursor = database.query("houseobjects", null , null, null, null, null, null);
        while(cursor.moveToNext()){
            syllabaries.add(createFromCursor(cursor));
        }
        return syllabaries;
    }

    private Kanji createFromCursor(Cursor cursor){
        Kanji kanji = new Kanji();

        kanji.setId(cursor.getInt(cursor.getColumnIndex("idQuestion")));
        kanji.setSyllabary(cursor.getString(cursor.getColumnIndex("Question")));
        kanji.setTranslation(cursor.getString(cursor.getColumnIndex("Answer")));
        int checkForGrade = cursor.getColumnIndex("Grade");
        if(checkForGrade == -1){
            return kanji;
        }
        kanji.setGrade(cursor.getInt(checkForGrade));

        return kanji;
    }
}
