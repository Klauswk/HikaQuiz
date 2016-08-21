package com.hika.activity;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hika.R;

public class ActivityQuestionnaire extends Activity{
	private SQLiteDatabase database;
	private Cursor cursor;
	private int Key;
	private Bundle bundle;
	private TextView tvQuestion;
	private EditText etAnswer;
	private Button btAnswer;
	private String Resposta;
	private int PerguntaGerada;
	private SharedPreferences shared;
	private int grade = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bundle = getIntent().getExtras();
		Key = bundle.getInt("Choosed");
		grade = bundle.getInt("GRADE");
		database = openOrCreateDatabase("Hirakadb.db", MODE_PRIVATE, null);

		switch (Key)
		{
		case 1:
			shared = getSharedPreferences("Options", MODE_PRIVATE);
			String checkboxes = shared.getString("options", "");
			StringBuilder sb = new StringBuilder();
			String[] array = checkboxes.split("#");
			sb.append("key_chooser in(");
			sb.append(array[1]);
			for (int i = 2; i < array.length; i++) {
				sb.append(",");
				sb.append(array[i]);
			}
			sb.append(")");
			cursor = database.query("questionnaire", new String[]{"Question","Answer"}, sb.toString(), null, null, null, null);
			cursor.moveToFirst();
			break;

		case 2:
			cursor = database.query("kanjis", null , "grade = " + String.valueOf(grade), null, null, null, null);
			cursor.moveToFirst();
			break;

		case 3:
			cursor = database.query("animals", null , null, null, null, null, null);
			cursor.moveToFirst();
			break;

		case 4:
			cursor = database.query("clothes", null , null, null, null, null, null);
			cursor.moveToFirst();
			break;

		case 5:
			cursor = database.query("houseobjects", null , null, null, null, null, null);
			cursor.moveToFirst();
			break;
		}
		setContentView(R.layout.activity_questionnaire);
		Casts();
		tvQuestion.setTextSize(100);
		etAnswer.setTextSize(30);
		GeraPergunta();
		Main();
	}

	private void Casts()
	{
		tvQuestion = (TextView) findViewById(R.id.tvQuestion);
		etAnswer = (EditText) findViewById(R.id.etAnswer);
		btAnswer = (Button) findViewById(R.id.btAnswer);
	}

	private int gerarNumero(int inicio, int maximo)
	{
		Random random = new Random();
		if (inicio > maximo) {
			while(inicio>maximo)
			{
				inicio--;
			}
		}
		long extensao = (long)maximo - (long)inicio + 1;
		long fraction = (long)(extensao * random.nextDouble());
		int Numerogerado =  (int)(fraction + inicio);    
		return Numerogerado;
	}

	private void GeraPergunta()
	{
		PerguntaGerada = gerarNumero(0,cursor.getCount()-1);
		cursor.moveToFirst();
		for (int i = 0; i < PerguntaGerada; i++) {
			cursor.moveToNext();
		}
		tvQuestion.setText(cursor.getString(cursor.getColumnIndex("Question")));
		Resposta = cursor.getString(cursor.getColumnIndex("Answer"));
		Resposta = Resposta.toLowerCase(new Locale("English"));
	}

	private void Main()
	{
		btAnswer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(Key == 2)
				{
					boolean Bresposta = false;
					String [] Respostas = Resposta.split(",");
					for (int i = 0; i < Respostas.length; i++) {
						if((" "+etAnswer.getText().toString()).contains(Respostas[i]))
						{
							Toast.makeText(getBaseContext(), "Right Answer!", Toast.LENGTH_SHORT).show();
							Bresposta = true;
						}
					}
					if(!Bresposta)
					{
						Toast.makeText(getBaseContext(), "Wrong Answer, right one: " + Resposta, Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					if(etAnswer.getText().toString().contains(Resposta))
					{
						Toast.makeText(getBaseContext(), "Right Answer!", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(getBaseContext(), "Wrong Answer, right one: " + Resposta, Toast.LENGTH_SHORT).show();
					}
				}
				etAnswer.setText("");
				GeraPergunta();
			}
		});
	}
}
