package br.edu.fa7.fightingbet.client;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import br.edu.fa7.fightingbet.model.BaseModel;
import br.edu.fa7.fightingbet.model.Usuario;
import br.edu.fa7.fightingbet.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SessionsClient {

	private static SessionsClient instance;
	
    private SessionsClient() {
    	
    }
    
    public static SessionsClient getInstace() {
    	if(instance == null) {
    		instance = new SessionsClient();
    	}
    	return instance;
    }
    
    public Usuario login(String email, String password) {
    	Gson gson = new Gson();
    	Usuario usuario = null;
    	
    	try {
			JsonObject root = new JsonObject();
			root.add("usuario", gson.toJsonTree(new Usuario(email, password), Usuario.class));
			
			StringEntity se = new StringEntity( root.toString() );
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			
			DefaultHttpClient client = new DefaultHttpClient();
			
			HttpPost post = new HttpPost( Constantes.BASE_URL + "/sessions" );
			post.addHeader("Content-Type", "application/json");
			post.addHeader("Accept", "application/json");
			post.setEntity(se);
			
			HttpResponse response = client.execute(post);
			InputStream inputStream = response.getEntity().getContent();
			
			String json = IOUtils.toString(inputStream, "UTF-8");
			usuario = gson.fromJson(json, Usuario.class);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return usuario;
    }

	public boolean logout(String token) {
		Gson gson = new Gson();
		DefaultHttpClient client = new DefaultHttpClient();
        
        HttpDelete delete = new HttpDelete( Constantes.BASE_URL + "/sessions/" + token );
        delete.addHeader("Content-Type", "application/json");
        delete.addHeader("Accept", "application/json");
        
        HttpResponse response;
        InputStream inputStream;
		
        try {
			response = client.execute(delete);
			inputStream = response.getEntity().getContent();

			String json = IOUtils.toString(inputStream, "UTF-8");
			BaseModel retorno = gson.fromJson(json, BaseModel.class);

			return !retorno.isError();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return false;
	}
}
