package Utils;

import java.util.List;

import android.app.Activity;

import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;

public class UtilsMetodos {

	private static UtilsMetodos utils;

	private UtilsMetodos(){}

	public static UtilsMetodos getInscace(){
		
		if(utils == null){
			utils = new UtilsMetodos();
		}

		return utils;
	}
	
	public boolean isConectado(){
		boolean retorno = false;

		Session session = Session.getActiveSession();
		if(session != null && session.isOpened()){
			retorno = true;
		}

		return retorno;
	}
	
	public boolean validarUsuario(Activity contexto){
		boolean retorno = false;
		
			
			Session session = Session.getActiveSession();
			List<String> permission = session.getPermissions();
			List<String> newPermission = UtilsConstants.permissions_app;

			if(hasNotPermission(permission,newPermission)){
				Session.NewPermissionsRequest newPermiRequest = new NewPermissionsRequest(contexto, newPermission);
				session.requestNewPublishPermissions(newPermiRequest);
			}else{
				retorno = true;
			}
			
		return retorno;
	}
	
	private boolean hasNotPermission(List<String> permission,
			List<String> newPermission) {

		boolean retorno = true;

		for(String thisPermission : permission){

			for(String newPer : newPermission){

				if(!thisPermission.equals(newPer)){
					retorno = false;
				}

			}
		}


		return retorno;
	}
 


}
