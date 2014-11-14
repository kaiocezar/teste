package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class DefinirTimesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.definir_times);

		final List<JogadorForList> jogadores = new ArrayList<JogadorForList>();

		for (int i = 0; i < 20; i++) {
			JogadorForList mapa = new JogadorForList();
			mapa.setNome("kaio" + i);
			mapa.setTipoTela("0");

			jogadores.add(mapa);
		}


		ListView view = (ListView) findViewById(R.id.jogadores);

		view.setAdapter(new BaseAdapterOnLance(this, jogadores));
		
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				LinearLayout lay = (LinearLayout) arg1.findViewById(R.id.content);
				
				JogadorForList joga = jogadores.get(arg2);
				if (joga.getTipoTela().equals("0")) {

					 lay.setBackgroundColor(Color.RED);
					joga.setTipoTela("1");

				} else if (joga.getTipoTela().equals("1")) {
					 lay.setBackgroundColor(Color.BLUE);
					joga.setTipoTela("2");
				} else {
					 lay.setBackgroundColor(Color.WHITE);
					joga.setTipoTela("0");
				}

			}
		});
		
		
		

	}
}
