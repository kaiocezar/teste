package com.app.onlance;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;


public class MainActivity extends Activity {

	UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new StatusCallback() {
		
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			
			onSessionStateChaged(session, state, exception);
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LoginButton lb = (LoginButton) findViewById(R.id.login_face);
        lb.setPublishPermissions(Arrays.asList("email","public_profile","user_friends"));
        
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);
    }
    
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	Session session = Session.getActiveSession();
    	if(session != null && (session.isOpened() || session.isClosed())){
    		onSessionStateChaged(session, session.getState(), null);
    	}
    	
    	
    	uiHelper.onResume();
    }
    

    @Override
    protected void onPause() {
    	super.onPause();
    	uiHelper.onPause();
    }
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	uiHelper.onDestroy();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	uiHelper.onSaveInstanceState(outState);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	uiHelper.onActivityResult(requestCode, resultCode, data);
    }
    
    
//    public void entrarFacebook(View view){
//    	   // start Facebook Login
//    	 Log.i("Script", "entrar");
//        Session.openActiveSession(this, true, new Session.StatusCallback() {
//
//          // callback when session changes state
//          @Override
//          public void call(Session session, SessionState state, Exception exception) {
//        	  Log.i("Script", "chamou o callback");
//        	  if (session.isOpened()) {
//        		  Log.i("Script", "secaoAberta");
//              // make request to the /me API
//              Request.newMeRequest(session, new Request.GraphUserCallback() {
//
//                // callback after Graph API response with user object
//                @Override
//                public void onCompleted(GraphUser user, Response response) {
//                  if (user != null) {
//                	  
//                	  Log.i("Script", user.getName());
//                  }
//                }
//              }).executeAsync();
//            }
//          }
//        });
//    	
//    	
//    }

    
    public void proximaTela(View view){
    	Intent intent =  new Intent(this, ModoJogoActivity.class);
    	
    	startActivity(intent);
    }
    
    public void onSessionStateChaged(final Session session, SessionState state, Exception exception){
    	
    	if(session != null && session.isOpened()){
    		Log.i("Script", "Usuario conectado");
    		Request.newMeRequest(session, new Request.GraphUserCallback() {
				
				@Override
				public void onCompleted(GraphUser user, Response response) {
				
					if(user != null){
						Log.i("Script", "Nome" + user.getName());
						Log.i("Script", "NomeFirst" + user.getFirstName());
						Log.i("Script", "Email" + user.getProperty("email"));
						Log.i("Script", "ID" + user.getId());
						getMyFriends(session);
						
					}
					
				}
			}).executeAsync();
    		
    	}else{
    		Log.i("Script", "Usuario Não conectado");
    	}
    	
    }
    
    public void getMyFriends(Session session){
    	Request.newMyFriendsRequest(session, new Request.GraphUserListCallback() {
			
			@Override
			public void onCompleted(List<GraphUser> users, Response response) {
				if(users != null){
					Log.i("Script", "NUmero de amigos" + users.size());
				}
				
				Log.i("Script", "Response" + response);
			}
		}).executeAsync();
    }

}
