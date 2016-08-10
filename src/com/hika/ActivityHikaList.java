package com.hika;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityHikaList extends Activity{
	GridView gv;
	SQLiteDatabase database;
	Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] teste = {" ", "a" , "b"};
		StringBuilder sb = new StringBuilder();
		database = openOrCreateDatabase("Hirakadb.db", MODE_PRIVATE, null);
		
		cursor = database.query("questionnaire", new String[]{"Question","Answer"}, null, null, null, null, null);
		cursor.moveToFirst();
		
		while(cursor.moveToNext())
		{
			sb.append(cursor.getString(cursor.getColumnIndex("Question")));
			sb.append("#");
		}
		
		teste = sb.toString().split("#");
		gv= new GridView(this);
		gv.setNumColumns(4);
		
		gv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,teste));
		
		setContentView(gv);
		
		gv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
					String texto = ((TextView) v).getText().toString();
					
					cursor = database.query("questionnaire", null , "Question = '" +texto + "'", null, null, null, null);
					cursor.moveToFirst();
					
					Toast.makeText(getBaseContext(), cursor.getString(cursor.getColumnIndex("Answer")), Toast.LENGTH_SHORT).show();
				}
		});
	}
}
