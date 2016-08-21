package com.hika.activity;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hika.R;
import com.hika.dao.KanjiDAO;
import com.hika.impl.KanjiDAOImpl;
import com.hika.model.Syllabarie;
import com.hika.service.QuestionnaraireService;
import com.hika.service.QuestionnaraireServiceImpl;

public class ActivityQuestionnaire extends Activity{

	private int Key;
	private Bundle bundle;
	private TextView tvQuestion;
	private EditText etAnswer;
	private Button btAnswer;
	private String Resposta;
	private int PerguntaGerada;
	private int grade = 1;

	private QuestionnaraireService questionnaraireService;
	private KanjiDAO kanjiDAO;
	private List<Syllabarie> syllabaries;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire);

		questionnaraireService = new QuestionnaraireServiceImpl();


		kanjiDAO = new KanjiDAOImpl(openOrCreateDatabase("Hirakadb.db", MODE_PRIVATE, null));

		bundle = getIntent().getExtras();
		Key = bundle.getInt("Choosed");
		grade = bundle.getInt("GRADE");

		switch (Key)
		{
			case 1:
				syllabaries = kanjiDAO.getFilteredHirakanaKatakanaList(getSharedPreferences("Options", MODE_PRIVATE));
				break;

			case 2:
				syllabaries = kanjiDAO.getFilteredKanjiList(String.valueOf(grade));
				break;

			case 3:
				syllabaries = kanjiDAO.getAnimalList();
				break;

			case 4:
				syllabaries = kanjiDAO.getClothList();
				break;

			case 5:
				syllabaries = kanjiDAO.getHouseThingList();
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

	private void GeraPergunta()
	{
		PerguntaGerada = questionnaraireService.randomNumber(0,syllabaries.size()-1);

		Syllabarie syllabary = syllabaries.get(PerguntaGerada);
		tvQuestion.setText(syllabary.getSyllabary());
		Resposta = syllabary.getTranslation();
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
