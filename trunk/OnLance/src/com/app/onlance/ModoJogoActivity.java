package com.app.onlance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ModoJogoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modo_jogo);
        
    }

    
    public void proximaTela(View view){
    	
    	Intent intent =  new Intent(this, ConfigActivity.class);
    	
    	startActivity(intent);
    	
    	
    	
    }
	
}
