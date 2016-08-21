package com.hika.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hika.R;
import com.hika.view.KanjiChooserDialog;
import com.hika.view.QuestionnarieCall;

public class ActivityQuizChooser extends Activity implements QuestionnarieCall {

	private TextView tvHika, tvKanjis, tvAnimals, tvClothes, tvHouseThings;
	public static String KEY_CHOOSER = "Choosed";
	private KanjiChooserDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizchooser);
		dialog = new KanjiChooserDialog(this,this);
		casts();
		Main();
	}

	private void casts()
	{
		tvHika = (TextView) findViewById(R.id.tvHika);
		tvKanjis = (TextView) findViewById(R.id.tvKanjis);
		tvAnimals = (TextView) findViewById(R.id.tvAnimals);
		tvClothes = (TextView) findViewById(R.id.tvClothes);
		tvHouseThings = (TextView) findViewById(R.id.tvHouseThings);
	}

	private void Main()
	{

		tvHika.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putInt(KEY_CHOOSER, 1);
				Intent intent = new Intent(getBaseContext(),ActivityQuestionnaire.class);
				intent.putExtras(b);
				startActivity(intent);
			}
		}); 

		tvKanjis.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.show();
			}
		}); tvAnimals.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putInt(KEY_CHOOSER, 3);
				Intent intent = new Intent(getBaseContext(),ActivityQuestionnaire.class);
				intent.putExtras(b);
				startActivity(intent);
			}
		}); tvClothes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putInt(KEY_CHOOSER, 4);
				Intent intent = new Intent(getBaseContext(),ActivityQuestionnaire.class);
				intent.putExtras(b);
				startActivity(intent);
			}
		}); tvHouseThings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putInt(KEY_CHOOSER, 5);
				Intent intent = new Intent(getBaseContext(),ActivityQuestionnaire.class);
				intent.putExtras(b);
				startActivity(intent);
			}
		});
	}

	@Override
	public void callQuestionnaire(int num)
	{
		Bundle b = new Bundle();
		b.putInt(KEY_CHOOSER, 2);
		b.putInt("GRADE", num);
		Intent intent = new Intent(getBaseContext(),ActivityQuestionnaire.class);
		intent.putExtras(b);
		startActivity(intent);
	}
}
