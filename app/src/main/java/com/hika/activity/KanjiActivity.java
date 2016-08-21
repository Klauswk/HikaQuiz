package com.hika.activity;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.hika.R;
import com.hika.dao.KanjiDAO;
import com.hika.impl.KanjiDAOImpl;
import com.hika.model.Kanji;

public class KanjiActivity extends Activity {

    private String modificador = ">";
    private RadioButton cbTranslation, cbGrade;
    private GridView gvKanjis;
    private EditText etPesquisa;
    private Button btPesquisar;
    private Spinner spEscolha;
    private RadioGroup rg;
    private KanjiDAO kanjiDAO;
    private ArrayAdapter<Kanji> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_tabelakanjis);

        kanjiDAO = new KanjiDAOImpl(openOrCreateDatabase("Hirakadb.db", MODE_PRIVATE, null));
        rg = (RadioGroup) findViewById(R.id.rg);
        cbTranslation = (RadioButton) findViewById(R.id.cbTranslation);
        cbGrade = (RadioButton) findViewById(R.id.cbGrade);
        etPesquisa = (EditText) findViewById(R.id.etPesquisa);
        btPesquisar = (Button) findViewById(R.id.btPesquisar);
        gvKanjis = (GridView) findViewById(R.id.gvKanji);
        spEscolha = (Spinner) findViewById(R.id.spEscolha);
        adapter = new ArrayAdapter<Kanji>(this,
                android.R.layout.simple_list_item_1, new Kanji[]{});

        ArrayAdapter<CharSequence> Spinneradapter = ArrayAdapter.createFromResource(this, R.array.escolhas_array, android.R.layout.simple_spinner_item);
        Spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEscolha.setAdapter(Spinneradapter);
        gvKanjis.setAdapter(adapter);
        cbGrade.setChecked(true);
        etPesquisa.setText("");
        btPesquisar.setText("Search");
        cbTranslation.setText("Translate");
        cbGrade.setText("Grade");

        Main();
    }

    private void Main() {

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                adapter = new ArrayAdapter<Kanji>(getBaseContext(),
                        android.R.layout.simple_list_item_1, kanjiDAO.getFilteredKanjiList(etPesquisa.getText().toString(), spEscolha.getVisibility() == View.INVISIBLE ? null : modificador));

                gvKanjis.setAdapter(adapter);
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cbTranslation) {
                    spEscolha.setVisibility(View.INVISIBLE);
                    etPesquisa.setInputType(1);
                    etPesquisa.setText("");
                } else {
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
                Toast.makeText(getBaseContext(), adapter.getItem(position).getTranslation(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
