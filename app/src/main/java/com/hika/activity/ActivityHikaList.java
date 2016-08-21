package com.hika.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.hika.dao.BaseSyllabariesDAO;
import com.hika.impl.BaseSyllabariesDAOImpl;
import com.hika.model.Syllabarie;

public class ActivityHikaList extends Activity{

	private GridView gv;
	private BaseSyllabariesDAO baseSyllabariesDAO;
	private ArrayAdapter<Syllabarie> syllabarieArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		baseSyllabariesDAO = new BaseSyllabariesDAOImpl(getBaseContext(),null);

		gv= new GridView(this);
		gv.setNumColumns(4);
		syllabarieArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, baseSyllabariesDAO.getAllSyllabaries());

		gv.setAdapter(syllabarieArrayAdapter);

		setContentView(gv);

		gv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
									int position, long id) {

				Toast.makeText(getBaseContext(),syllabarieArrayAdapter.getItem(position).getTranslation(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
