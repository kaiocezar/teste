package com.app.onlance;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;

public class SimpleAdapterLance extends SimpleAdapter{

	public SimpleAdapterLance(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
					int[] to) {
		super(context, data, resource, from, to);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView!= null){
			EditText tipoTela = (EditText) convertView
					.findViewById(R.id.tipoTelaNum);

			if (tipoTela.getText().toString().equals("0")) {

				convertView.setBackgroundColor(Color.RED);
				tipoTela.setText("1");
            
            

			} else if (tipoTela.getText().toString().equals("1")) {
				convertView.setBackgroundColor(Color.BLUE);
				tipoTela.setText("2");
			} else {
				convertView.setBackgroundColor(Color.WHITE);
				tipoTela.setText("0");
			}
		}

		return super.getView(position, convertView, parent);
	}

}
