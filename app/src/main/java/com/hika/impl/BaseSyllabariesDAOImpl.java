package com.hika.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hika.dao.BaseSyllabariesDAO;
import com.hika.model.Syllabarie;
import com.hika.model.SyllabaryType;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
public class BaseSyllabariesDAOImpl implements BaseSyllabariesDAO {

    private Context context;
    private SQLiteDatabase database;

    public BaseSyllabariesDAOImpl(Context context, SQLiteDatabase database) {
        this.context = context;
        this.database = context.openOrCreateDatabase("Hirakadb.db", MODE_PRIVATE, null);
    }

    @Override
    public List<Syllabarie> getAllSyllabaries() {
        Cursor cursor = database.query("questionnaire", null, null, null, null, null, null);
        cursor.moveToFirst();

        ArrayList<Syllabarie> syllabaries = new ArrayList<>();

        while(cursor.moveToNext())
        {
            syllabaries.add(createFromCursor(cursor));
        }

        cursor.close();
        return syllabaries;
    }

    @Override
    public List<Syllabarie> getSyllabarieByType(SyllabaryType syllabaryType) {
        return null;
    }

    private Syllabarie createFromCursor(Cursor cursor){
        Syllabarie syllabarie = new Syllabarie();

        syllabarie.setId(cursor.getInt(cursor.getColumnIndex("idQuestion")));
        syllabarie.setSyllabary(cursor.getString(cursor.getColumnIndex("Question")));
        syllabarie.setTranslation(cursor.getString(cursor.getColumnIndex("Answer")));
        int key_chooser = cursor.getInt(cursor.getColumnIndex("key_chooser")) - 1;
        syllabarie.setSyllabaryType(SyllabaryType.values()[key_chooser > 1 ? 0 : key_chooser]);

        return syllabarie;
    }
}
