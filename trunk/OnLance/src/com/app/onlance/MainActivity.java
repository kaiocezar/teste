package com.app.onlance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }



    public void entrar(View view){
    	   // start Facebook Login
    	 Log.i("Script", "entrar");
        Session.openActiveSession(this, true, new Session.StatusCallback() {

          // callback when session changes state
          @Override
          public void call(Session session, SessionState state, Exception exception) {
        	  Log.i("Script", "chamou o callback");
        	  if (session.isOpened()) {
        		  Log.i("Script", "secaoAberta");
              // make request to the /me API
              Request.newMeRequest(session, new Request.GraphUserCallback() {

                // callback after Graph API response with user object
                @Override
                public void onCompleted(GraphUser user, Response response) {
                  if (user != null) {
                	  
                	  Log.i("Script", user.getName());
                  }
                }
              }).executeAsync();
            }
          }
        });
    	
    	
    }
    public void sair(View view){
    	
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
    
    public void configuracao(View view){
    	Intent intent =  new Intent(this, ConfigActivity.class);
    	
    	startActivity(intent);
    	
    	
    }

}
