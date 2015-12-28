package br.edu.fa7.fightingbet.client;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import br.edu.fa7.fightingbet.model.Noticia;
import br.edu.fa7.fightingbet.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class NoticiasClient {

	private static NoticiasClient instance;
	
    private NoticiasClient() {
    	
    }
    
    public static NoticiasClient getInstace() {
    	if (instance == null) {
    		instance = new NoticiasClient();
    	}
    	return instance;
    }
    
    public List<Noticia> list(String token) {
    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    	List<Noticia> noticias = null;
    	
    	try {
			DefaultHttpClient client = new DefaultHttpClient();
			
			HttpGet get = new HttpGet( Constantes.BASE_URL + "/noticias?auth_token=" + token );
			get.addHeader("Content-Type", "application/json");
			get.addHeader("Accept", "application/json");
			
			HttpResponse response = client.execute(get);
			InputStream inputStream = response.getEntity().getContent();
			
			Type noticiasListType = new TypeToken<List<Noticia>>(){}.getType();
			
			String json = IOUtils.toString(inputStream, "UTF-8");
			noticias = gson.fromJson(json, noticiasListType);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return noticias;
    }
}
