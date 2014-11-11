package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PartidaActivity extends Activity {

	Chronometer cronometro;
	boolean teste = true;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
     setContentView(R.layout.partida);
     
     List<String> jogadore = new ArrayList<String>();
     List<String> jogadore2 = new ArrayList<String>();
     
     
     jogadore.add("Kaio");
     jogadore.add("João");
     jogadore.add("zé");
     jogadore.add("geyson");

     jogadore2.add("Kaio");
     jogadore2.add("João");
     jogadore2.add("zé");
     jogadore2.add("geyson");
     
     
     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,jogadore);
     ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,jogadore);
     
     ListView view = (ListView) findViewById(R.id.time1);
     ListView view2 = (ListView) findViewById(R.id.time2);
     
     view.setAdapter(adapter);
     view2.setAdapter(adapter2);
     cronometro = (Chronometer) findViewById(R.id.chronometer1);
     cronometro.setOnChronometerTickListener(new OnChronometerTickListener() {
		
		@Override
		public void onChronometerTick(Chronometer arg0) {
			
			String limite = "00:20";
			
			String valorCronometro = arg0.getText().toString();
			if(valorCronometro.equals(limite)){
				Toast.makeText(PartidaActivity.this, "fim", Toast.LENGTH_LONG).show();
				cronometro.stop();
				
			}
		}
	});
     
     
	}
	
	public void start(View view){
		cronometro.setBase(SystemClock.elapsedRealtime());
		cronometro.start();
		if(teste){
			Toast.makeText(this, String.valueOf(cronometro.getBase()), Toast.LENGTH_LONG).show();
			teste =false;
		}else{
			Toast.makeText(this, cronometro.getText(), Toast.LENGTH_LONG).show();
			teste =true;
		}
	}
}
