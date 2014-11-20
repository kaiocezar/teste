package com.app.onlance;

import java.util.List;

import Utils.UtilsConstants;
import Utils.UtilsMetodos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.HttpMethod;
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
//	private boolean reauth = false;
//	private static final String KEY = "reauth";
	
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
		lb.setPublishPermissions(UtilsConstants.permissions_app);

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);

		if(UtilsMetodos.getInscace().isConectado() && UtilsMetodos.getInscace().validarUsuario(this)){
			proximaTelaEvent();
		}
		
//		if(savedInstanceState != null){
//			reauth = savedInstanceState.getBoolean(KEY, false);
//		}
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
//		outState.putBoolean(KEY, reauth);
		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
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

	public void proximaTela(View view){
		
//		shareContent();
		
		proximaTelaEvent();
	}

	public void proximaTelaEvent(){
		Intent intent =  new Intent(this, ModoJogoActivity.class);

		startActivity(intent);
	}


	public void shareContent(){
		if(UtilsMetodos.getInscace().isConectado() && UtilsMetodos.getInscace().validarUsuario(this)){

			Session session = Session.getActiveSession();
			Bundle paramns = new Bundle();
			paramns.putString("name", "TesteNome");
			paramns.putString("caption", "Teste SubTitulo");
			paramns.putString("description", "Teste descrição");
			paramns.putString("link", "http://google.com.br");
			paramns.putString("picture", "http://www.informatoz.com/imagens/ico_cobertura/bola.png");


			Request.Callback call = new Request.Callback() {

				@Override
				public void onCompleted(Response response) {
					if(response.getError() == null){
						Toast.makeText(MainActivity.this, "Sucesso", Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(MainActivity.this, "Falha", Toast.LENGTH_LONG).show();
					}
				}
			};

			Request re = new Request(session,"/me/feed",paramns,HttpMethod.POST,call);
			re.executeAsync();
		}

	}


	
	

}
