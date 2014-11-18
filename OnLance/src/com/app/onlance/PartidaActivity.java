package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PartidaActivity extends Activity {
	private static final String limite = "00:20";
	
	Chronometer cronometro;
	private long milliseconds;
	private boolean isStart;
	private boolean isGol = false;
	
	List<String> jogadores1;
	List<String> jogadores2;
	ListView listViewJogadores1; 
	ListView listViewJogadores2; 
	
	TextView placar1;
	TextView placar2;
	
	Button gol;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partida);
		init();
		bind();
	}
	
	public void init(){
		
		jogadores1 = new ArrayList<String>();
		jogadores2 = new ArrayList<String>();
		milliseconds = 0;
		isStart = true;
		
		jogadores1.add("Geyson");
		jogadores1.add("Kaio");
		jogadores1.add("Zé Carlos");
		jogadores1.add("João Vitor");

		jogadores2.add("Helton");
		jogadores2.add("3xato");
		jogadores2.add("Natan");
		jogadores2.add("Tertius");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, jogadores1);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, jogadores2);

		listViewJogadores1 = (ListView) findViewById(R.id.time1);
		listViewJogadores2 = (ListView) findViewById(R.id.time2);

		listViewJogadores1.setAdapter(adapter);
		listViewJogadores2.setAdapter(adapter2);
		
		cronometro = (Chronometer) findViewById(R.id.chronometer1);
		gol = (Button) findViewById(R.id.gol);
		placar1 = (TextView) findViewById(R.id.placar1);
		placar2 = (TextView) findViewById(R.id.placar2);
	}
	
	
	public void bind(){
		
		cronometro.setOnChronometerTickListener(new OnChronometerTickListener() {

			@Override
			public void onChronometerTick(Chronometer arg0) {

				String valorCronometro = arg0.getText().toString();
				if (valorCronometro.equals(limite)) {
					Toast.makeText(PartidaActivity.this, "fim",
							Toast.LENGTH_LONG).show();
					cronometro.stop();
					milliseconds = 0;
					isStart = true;
				}
			}
		});
		
		listViewJogadores1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				if(isGol){
					Integer gol = Integer.parseInt(placar1.getText().toString());
					gol++;
					placar1.setText(gol.toString());
					modificarGol();
				}
				
			}
		});
		
		listViewJogadores2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				if(isGol){
					Integer gol = Integer.parseInt(placar2.getText().toString());
					gol++;
					placar2.setText(gol.toString());
					modificarGol();
				}
				
			}
		});
		
		
	}
	
	public void clickGol(View view){
		modificarGol();
	}
	
	public void modificarGol(){
		if(isGol){
			gol.setBackgroundResource(R.drawable.gol);
			isGol = false;
		}else{
			Toast.makeText(this, "Click no jogardor para marcar o gol", Toast.LENGTH_LONG).show();
			gol.setBackgroundResource(R.drawable.gol_ativo);
			isGol = true;
		}
		
	}

	public void start(View view) {
		if(isStart){
			cronometro.setBase(SystemClock.elapsedRealtime() - milliseconds);
			cronometro.start();
			isStart = false;
		}else{
			 milliseconds = SystemClock.elapsedRealtime() -cronometro.getBase();
			 cronometro.stop();
			 isStart = true;
		}
	}
	
}
