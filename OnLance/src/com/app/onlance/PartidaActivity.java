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
	private static final String limite = "00:20";
	
	Chronometer cronometro;
	private long milliseconds;
	private boolean isStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partida);

		List<String> jogadores1 = new ArrayList<String>();
		List<String> jogadores2 = new ArrayList<String>();

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

		ListView view = (ListView) findViewById(R.id.time1);
		ListView view2 = (ListView) findViewById(R.id.time2);

		view.setAdapter(adapter);
		view2.setAdapter(adapter2);
		cronometro = (Chronometer) findViewById(R.id.chronometer1);
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
