package com.app.onlance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DefinirTimesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.definir_times);

		List<HashMap<String, Object>> jogadores = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 20; i++) {
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("nome", "kaio" + i);
			mapa.put("tipoTela", "0");

			jogadores.add(mapa);
		}

		String[] from = new String[] { "nome", "tipoTela" };

		int[] to = new int[] { R.id.textPartida, R.id.tipoTelaNum };

		SimpleAdapterLance adap = new SimpleAdapterLance(this, jogadores,
				R.layout.layout_list_time, from, to);

		
		ListView view = (ListView) findViewById(R.id.jogadores);

		view.setAdapter(adap);
		
		view.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				EditText tipoTela = (EditText) arg1
						.findViewById(R.id.tipoTelaNum);

				if (tipoTela.getText().toString().equals("0")) {

					arg0.getChildAt(arg2).setBackgroundColor(Color.RED);
					// arg1.setBackgroundColor(Color.RED);
					tipoTela.setText("1");

				} else if (tipoTela.getText().toString().equals("1")) {
					arg0.getChildAt(arg2).setBackgroundColor(Color.BLUE);
					// arg1.setBackgroundColor(Color.BLUE);
					tipoTela.setText("2");
				} else {
					arg0.getChildAt(arg2).setBackgroundColor(Color.WHITE);
					// arg1.setBackgroundColor(Color.WHITE);
					tipoTela.setText("0");
				}

				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
			
			
		});
		

	}
}
