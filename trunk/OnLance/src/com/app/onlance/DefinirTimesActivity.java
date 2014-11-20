package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

import Utils.UtilsConstants;
import Utils.UtilsMetodos;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class DefinirTimesActivity extends Activity {

	List<JogadorForList> jogadores;
	Bundle savedInstanceState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.definir_times);

		jogadores = new ArrayList<JogadorForList>();
		
		
		this.savedInstanceState = getIntent().getExtras();
		
		
		if(UtilsMetodos.getInscace().isConectado()){
			getMyFriends(Session.getActiveSession());
		}

	}
	
	public void carregarListJogadores(){
		
		if(jogadores.size() == 0){
			for (int i = 0; i < 20; i++) {
				JogadorForList mapa = new JogadorForList();
				mapa.setNome("kaio" + i);
				mapa.setTipoTela("0");

				jogadores.add(mapa);
			}
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


	public void prosseguir(View view){

		Intent intent = new Intent(this, PartidaActivity.class);
		
		if(savedInstanceState !=null){
			intent.putExtras(savedInstanceState);
		}
		
		startActivity(intent);


	}

	public void getMyFriends(Session session){
		Request.newMyFriendsRequest(session, new Request.GraphUserListCallback() {

			@Override
			public void onCompleted(List<GraphUser> users, Response response) {
				if(response.getError() == null & users != null && users.size() > 0){
					Log.i("Script", "NUmero de amigos " + users.size());
					
					for(GraphUser amigos: users){
						
						JogadorForList mapa = new JogadorForList();
						mapa.setNome(amigos.getName());
						mapa.setTipoTela("0");
						jogadores.add(mapa);
					}
					carregarListJogadores();
					
				}

				Log.i("Script", "Response" + response);
			}
		}).executeAsync();
	}

}
