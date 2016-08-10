package com.hika;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btMainStartQuiz, btMainOptions, btKanjiList, btHiraList;
	private SharedPreferences shared;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		shared = getSharedPreferences("Options", MODE_PRIVATE);
		
		if(!shared.contains("options"))
		{
			Editor editor = shared.edit();
			editor.putString("options", "#1#2#3#4#5#6#7#8");
			editor.commit();
		}
		try {
			CopiarDatabase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i("Error", e.toString());
		}
		casts();

		btMainStartQuiz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(),ActivityQuizChooser.class));
			}
		});
		btMainOptions.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(),ActivityOptions.class));
				finish();
			}
		});
		
		btKanjiList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(),KanjiActivity.class));
			}
		});
		
		btHiraList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(),ActivityHikaList.class));
			}
		});
	}
	private void casts()
	{
		btMainStartQuiz = (Button) findViewById(R.id.btMainStartQuiz);
		btMainOptions = (Button) findViewById(R.id.btMainOptions);
		btKanjiList = (Button) findViewById(R.id.btKanjiList);
		btHiraList = (Button) findViewById(R.id.btkataList);
	}
	
	private void CopiarDatabase()
			throws IOException
			{
		try
		{
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
		}
		catch (Exception localException)
		{
			Log.e("error", localException.toString());
		}
			}
}
