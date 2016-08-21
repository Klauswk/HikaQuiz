package com.hika.impl;

import android.content.Context;
import android.content.Intent;

import com.hika.activity.ActivityHikaList;
import com.hika.activity.ActivityOptions;
import com.hika.activity.ActivityQuizChooser;
import com.hika.activity.KanjiActivity;
import com.hika.navigation.Navigation;
import com.hika.service.NavigationService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public class NavigationServiceImpl implements NavigationService {

    private List<Navigation> navigationList;
    private Context context;

    public NavigationServiceImpl(Context context) {

        this.context = context;
        navigationList = new ArrayList<>(5);
        navigationList.add(new Navigation("Quiz",new Intent(context, ActivityQuizChooser.class)));
        navigationList.add(new Navigation("Options",new Intent(context, ActivityOptions.class)));
        navigationList.add(new Navigation("Kanji List",new Intent(context, KanjiActivity.class)));
        navigationList.add(new Navigation("Hirakana / Katakana list",new Intent(context, ActivityHikaList.class)));

    }

    @Override
    public List<Navigation> getNavigationOptions() {
        return navigationList;
    }
}
