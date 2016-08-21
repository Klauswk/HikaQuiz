package com.hika;

import android.app.Application;
import android.util.Log;

import com.hika.dao.PreferencesDAO;
import com.hika.impl.PreferencesDAOImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public class HikaQuizApplication extends Application{

    private PreferencesDAO preferencesDAO;

    @Override
    public void onCreate() {
        super.onCreate();

        preferencesDAO = new PreferencesDAOImpl(getApplicationContext());

        try {
            CopiarDatabase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.i("Error", e.toString());
        }
    }

    private void CopiarDatabase()
            throws IOException {
        try {
            InputStream localInputStream = getAssets().open("db/Hirakadb.db");
            FileOutputStream localFileOutputStream = new FileOutputStream("/data/data/com.hika/databases/Hirakadb.db");

            byte[] buffer = new byte[1024];
            int length;
            while ((length = localInputStream.read(buffer)) > 0) {
                localFileOutputStream.write(buffer, 0, length);
            }

            // Close the streams
            localFileOutputStream.flush();
            localFileOutputStream.close();
            localInputStream.close();
        } catch (Exception localException) {
            Log.e("error", localException.toString());
        }
    }
}
