package com.hika.navigation;

import android.content.Intent;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public class Navigation {

    private String title;
    private Intent intent;

    public Navigation() {
    }

    public Navigation(String title, Intent intent) {
        this.title = title;
        this.intent = intent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @Override
    public String toString() {
        return title;
    }
}
