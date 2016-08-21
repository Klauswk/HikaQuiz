package com.hika.dao;

import android.content.SharedPreferences;

import com.hika.model.Kanji;
import com.hika.model.Syllabarie;

import java.util.List;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public interface KanjiDAO {

   public List<Kanji> getFilteredKanjiList(String query, String modification);
   public List<Syllabarie> getHouseThingList();
   public List<Syllabarie> getClothList();
   public List<Syllabarie> getFilteredHirakanaKatakanaList(SharedPreferences shared);
   public List<Syllabarie> getFilteredKanjiList(String grade);
   public List<Syllabarie> getAnimalList();

}
