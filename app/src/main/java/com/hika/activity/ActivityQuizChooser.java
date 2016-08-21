package com.hika.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hika.R;

public class ActivityQuizChooser extends Activity{

	private TextView tvHika, tvKanjis, tvAnimals, tvClothes, tvHouseThings;
	public static String KEY_CHOOSER = "Choosed";
	private Dialog dialog;
	private TextView[] grades;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizchooser);
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_kanjischose);
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
		grades = new TextView[7];
		grades[0] = (TextView) dialog.findViewById(R.id.tvGrade1);
		grades[1] = (TextView) dialog.findViewById(R.id.tvGrade2);
		grades[2] = (TextView) dialog.findViewById(R.id.tvGrade3);
		grades[3] = (TextView) dialog.findViewById(R.id.tvGrade4);
		grades[4] = (TextView) dialog.findViewById(R.id.tvGrade5);
		grades[5] = (TextView) dialog.findViewById(R.id.tvGrade6);
		grades[6] = (TextView) dialog.findViewById(R.id.tvGrade7);


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

				grades[0].setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						callQuestionnaire(1);
					}
				});

				grades[1].setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						callQuestionnaire(2);
					}
				});

				grades[2].setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						callQuestionnaire(3);
					}
				});

				grades[3].setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						callQuestionnaire(4);
					}
				});

				grades[4].setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						callQuestionnaire(5);
					}
				});

				grades[5].setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						callQuestionnaire(6);
					}
				});

				grades[6].setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						callQuestionnaire(7);
					}
				});
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
