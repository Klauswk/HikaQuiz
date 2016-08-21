package com.hika.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.hika.R;

public class ActivityOptions extends Activity{
	private CheckBox cbHira,cbKata,cbModHira,cbModKata,cbYouHira,cbYouKata;
	private Button btOptionsOK;
	private SharedPreferences shared;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opcoes);
		casts();
		shared = getSharedPreferences("Options", MODE_PRIVATE);
		String checkboxes = shared.getString("options", "");
		Log.i("Kana", checkboxes);
		String[] checados = checkboxes.split("#");

		for (int i = 0; i < checados.length; i++) {
			Log.i("Kana", checados[i]);

			if(checados[i].contains("1"))
			{
				cbHira.setChecked(true);
			}
			if(checados[i].contains("2"))
			{
				cbKata.setChecked(true);
			}
			if(checados[i].contains("3"))
			{
				cbModHira.setChecked(true);
			}
			if(checados[i].contains("4"))
			{
				cbModKata.setChecked(true);
			}
			if(checados[i].contains("5"))
			{
				cbYouHira.setChecked(true);
			}
			if(checados[i].contains("6"))
			{
				cbYouKata.setChecked(true);
			}
			if(checados[i].contains("7"))
			{

			}
		}

		btOptionsOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				StringBuilder sb = new StringBuilder();
				if(cbHira.isChecked())
				{
					sb.append("#");
					sb.append("1");
				}
				if(cbKata.isChecked())
				{
					sb.append("#");
					sb.append("2");
				}
				if(cbModHira.isChecked())
				{
					sb.append("#");
					sb.append("3");
				}
				if(cbModKata.isChecked())
				{
					sb.append("#");
					sb.append("4");
				}
				if(cbYouKata.isChecked())
				{
					sb.append("#");
					sb.append("6");
				}
				if(cbYouHira.isChecked())
				{
					sb.append("#");
					sb.append("5");
				}
				if(sb.length() < 2)
				{
					Toast.makeText(getBaseContext(), "You need choose at least one!", Toast.LENGTH_LONG).show();
				}
				else
				{
					Editor editor = shared.edit();
					Log.i("Kana", sb.toString());
					editor.putString("options", sb.toString());
					editor.commit();

					startActivity(new Intent(getBaseContext(),MainActivity.class));
					finish();
				}
			}
		});
	}

	private void casts()
	{
		cbHira = (CheckBox) findViewById(R.id.cbHira);
		cbKata = (CheckBox) findViewById(R.id.cbKata);
		cbModHira = (CheckBox) findViewById(R.id.cbModHira);
		cbModKata = (CheckBox) findViewById(R.id.cbModKata);
		cbYouHira = (CheckBox) findViewById(R.id.cbYouHira);
		cbYouKata = (CheckBox) findViewById(R.id.cbYouKata);
		btOptionsOK = (Button) findViewById(R.id.btOptionsOK);
	}
}
