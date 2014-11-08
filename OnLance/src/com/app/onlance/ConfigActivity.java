package com.app.onlance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ConfigActivity extends Activity{

	String[] quantJogadores = new String[]{"4","5","6","7","8","9","10","11","12"};
	String[] quantGols = new String[]{"1","2","3","4","5"};
	String[] quantTempo = new String[]{"5 minutos","10 minutos","15 minutos",
			"20 minutos","25 minutos","30 minutos","35 minutos","40 minutos","45 minutos"};
	Spinner spinnerQuantJog;
	Spinner spinnerQuantGols;
	Spinner spinnerQuantTemp;
	
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.configuracoes_partidas);
	        
	        ArrayAdapter<String> adapterJog = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,quantJogadores);
	        ArrayAdapter<String> adapterGols = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,quantGols);
	        ArrayAdapter<String> adapterTemp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,quantTempo);
	        
	        spinnerQuantGols = (Spinner) findViewById(R.id.quantGolSpnieer);
	        spinnerQuantJog = (Spinner) findViewById(R.id.quantJogSpnieer);
	        spinnerQuantTemp = (Spinner) findViewById(R.id.quantTempoSpnieer);
	        
	        spinnerQuantGols.setAdapter(adapterGols);
	        spinnerQuantTemp.setAdapter(adapterTemp);
	        spinnerQuantJog.setAdapter(adapterJog);
	        
	    }
	  
	  
	  
	  
	  public void prosseguir(View view){
		  Intent intent =  new Intent(this, TimesPartidaActivity.class);
	    	
	    	startActivity(intent);
		  
		  
	  }
}

