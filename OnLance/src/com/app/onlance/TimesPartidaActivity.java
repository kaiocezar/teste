package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TimesPartidaActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecao_times);
        
        List<String> jogadore = new ArrayList<String>();
        
        
        jogadore.add("Kaio");
        jogadore.add("João");
        jogadore.add("zé");
        jogadore.add("geyson");
        
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,jogadore);
        
        ListView view = (ListView) findViewById(R.id.listView1);
        view.setAdapter(adapter);
        
	}
	
	public void testeView(View view){
		
		 Log.i("Script", "teste viw");
		
		
	}
	
}
