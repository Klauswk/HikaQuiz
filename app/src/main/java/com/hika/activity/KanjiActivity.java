package com.hika.activity;


import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.hika.R;

public class KanjiActivity extends Activity{
	public static final String PREFS_NAME = "Preferencias";
	public static final String KEY_IDIOMAS = "Idiomas";
	private String modificador = ">";
	private String sKanjis[];
	private RadioButton cbTranslation, cbGrade;
	private GridView gvKanjis;
	private EditText etPesquisa;
	private Button btPesquisar;
	private Spinner spEscolha;
	private Cursor cursor = null;
	private SQLiteDatabase ListaKanjis;
	private RadioGroup rg;
	private SharedPreferences shared;
	private String[] textos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_tabelakanjis);
		shared = getSharedPreferences(PREFS_NAME, 0); //Nome do arquivo e tipo de edicação
		textos = getResources().getStringArray(R.array.en);
		criaBanco();
		buscarDados();
		rg = (RadioGroup) findViewById(R.id.rg);
		cbTranslation = (RadioButton) findViewById(R.id.cbTranslation);
		cbGrade = (RadioButton) findViewById(R.id.cbGrade);
		etPesquisa = (EditText) findViewById(R.id.etPesquisa);
		btPesquisar = (Button) findViewById(R.id.btPesquisar);
		gvKanjis = (GridView) findViewById(R.id.gvKanji);
		spEscolha = (Spinner) findViewById(R.id.spEscolha);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] {" "});
		ArrayAdapter<CharSequence> Spinneradapter = ArrayAdapter.createFromResource(this,R.array.escolhas_array, android.R.layout.simple_spinner_item);
		Spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spEscolha.setAdapter(Spinneradapter);
		gvKanjis.setAdapter(adapter);
		cbGrade.setChecked(true);
		etPesquisa.setText("");

		/**if(shared.getString("Idiomas", "").contains("Port"))
			{
				textos = getResources().getStringArray(R.array.pt);
			}
			else if(shared.getString("Idiomas", "").contains("Eng"))
			{**/
		textos = getResources().getStringArray(R.array.en);
		/**}
			else
			{
				textos = getResources().getStringArray(R.array.es);
			}**/

		btPesquisar.setText(textos[21]);
		cbTranslation.setText(textos[19]);
		cbGrade.setText(textos[20]);

		Main();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	private void Main()
	{

		btPesquisar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EmpurraInformacoes(etPesquisa.getText().toString(),rg.getCheckedRadioButtonId(), modificador);
				atualizaDados();
			}
		});
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.cbTranslation)
				{
					spEscolha.setVisibility(View.INVISIBLE);
					etPesquisa.setInputType(1);
					etPesquisa.setText("");
				}
				else
				{
					spEscolha.setVisibility(View.VISIBLE);
					etPesquisa.setInputType(2);
					etPesquisa.setText("");
				}
			}
		});

		spEscolha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				modificador = parent.getItemAtPosition(pos).toString();
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		gvKanjis.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				ProcuraInformacao(sKanjis[position]);
			}
		});
	}

	private void ProcuraInformacao(String kanji)
	{
		cursor = ListaKanjis.query("Kanjis", null, "Question like '" + kanji + "'", null, null, null, null);
		cursor.moveToFirst();

		Toast.makeText(getBaseContext(), cursor.getString(cursor.getColumnIndex("Answer")), Toast.LENGTH_LONG).show();
	}

	private void atualizaDados() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, sKanjis);

		gvKanjis.setAdapter(adapter);

	}

	private void criaBanco()
	{
		try{
			ListaKanjis  = openOrCreateDatabase("Hirakadb.db",MODE_PRIVATE,null);
		}
		catch(Exception err ){
			Toast.makeText(this, "Não foi possivel inicializar o Banco", Toast.LENGTH_LONG).show();
		}
	}

	private boolean buscarDados()
	{
		int indice = 0;
		try 
		{
			cursor = ListaKanjis.query("Kanjis", new String[] {"Question"} ,null, null, null,null,null);

			if(cursor.getCount() != 0)
			{
				sKanjis = new String[20];
				cursor.moveToFirst();
				while(indice < 10)
				{
					sKanjis[indice] = cursor.getString(cursor.getColumnIndex("Question"));
					cursor.moveToNext();
					indice++;
				}
				return true;
			}
			else
			{
				return false;
			}

		} 
		catch (Exception e) 
		{
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
			return false;
		}

	}

	private void EmpurraInformacoes(String pesquisa, int id, String mod)
	{
		if(id == R.id.cbTranslation)
		{
			cursor = ListaKanjis.rawQuery("SELECT * FROM " + "Kanjis" + " where Answer LIKE '" + pesquisa + "%'" , null);
			cursor.moveToFirst();
		}
		else if(id == R.id.cbGrade)
		{
			cursor = ListaKanjis.rawQuery("SELECT * FROM " + "Kanjis" + " where grade "+ mod + "'" + pesquisa + "'" , null);
			cursor.moveToFirst();
		}

		if(cursor.getCount() > 200)
		{
			sKanjis = new String[200];
			for (int i = 0; i < 200; i++) {
				sKanjis[i] = cursor.getString(cursor.getColumnIndex("Question"));
				cursor.moveToNext();
			}
		}
		else
		{
			sKanjis = new String[cursor.getCount()];
			for (int i = 0; i < sKanjis.length ; i++) {
				sKanjis[i] = cursor.getString(cursor.getColumnIndex("Question"));
				cursor.moveToNext();
			}
		}

	}
}
