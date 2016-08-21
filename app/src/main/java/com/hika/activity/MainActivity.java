package com.hika.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hika.R;
import com.hika.impl.NavigationServiceImpl;
import com.hika.navigation.Navigation;
import com.hika.service.NavigationService;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView lvOptions;
    private ArrayAdapter<Navigation> adapter;
    private NavigationService navigationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvOptions = (ListView) findViewById(R.id.lvOptions);
        navigationService = new NavigationServiceImpl(getBaseContext());
        adapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,navigationService.getNavigationOptions());
        lvOptions.setAdapter(adapter);
        lvOptions.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(adapter.getItem(position).getIntent());
    }

}
