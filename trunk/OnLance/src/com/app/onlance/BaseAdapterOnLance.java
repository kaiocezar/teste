package com.app.onlance;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseAdapterOnLance extends BaseAdapter {

	List<JogadorForList> jogadores;
	Context contexto;
	
	public BaseAdapterOnLance(Context contexto,List<JogadorForList> jogadores){
		super();
		this.contexto = contexto;
		this.jogadores = jogadores;
	}
	
	
	
	@Override
	public int getCount() {
		return jogadores.size();
	}

	@Override
	public Object getItem(int arg0) {
		return jogadores.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
	

	@Override
	public View getView(int position, View view, ViewGroup viewGrup) {

		JogadorForList jogaro = jogadores.get(position);
		View viewLine;
		if(view == null){
			LayoutInflater inflate = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			viewLine = inflate.inflate(R.layout.layout_list_time, null);
			
		}else{
			viewLine = view;
		}
		
		TextView text = (TextView) viewLine.findViewById(R.id.textPartida);
		text.setText(jogaro.getNome());
		
		LinearLayout lay = (LinearLayout) viewLine.findViewById(R.id.content);
		
		if(jogaro.getTipoTela().equals("0")){
			lay.setBackgroundColor(Color.WHITE);
			
		}else if(jogaro.getTipoTela().equals("1")){
			lay.setBackgroundColor(Color.RED);
		}else{
			lay.setBackgroundColor(Color.BLUE);
		}
		return viewLine;
	}
	

}
