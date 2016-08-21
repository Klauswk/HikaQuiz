package com.hika.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.hika.dao.PreferencesDAO;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
public class PreferencesDAOImpl implements PreferencesDAO {

    private Context context;
    private SharedPreferences shared;

    public PreferencesDAOImpl(Context context) {
        this.context = context;
        shared = context.getSharedPreferences("Options", Context.MODE_PRIVATE);

        if(checkIfIsFirstEntry()){
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("options", "#1#2#3#4#5#6#7#8");
            editor.commit();
        }
    }

    @Override
    public boolean checkIfIsFirstEntry() {
        return !shared.contains("options");
    }
}
