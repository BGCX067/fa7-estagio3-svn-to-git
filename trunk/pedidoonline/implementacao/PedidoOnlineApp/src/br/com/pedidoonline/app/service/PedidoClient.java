package br.com.pedidoonline.app.service;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import br.com.pedidoonline.app.model.Cardapio;
import br.com.pedidoonline.app.model.Pedido;

import com.google.gson.Gson;

public class PedidoClient {

	private static PedidoClient instance;
	private String cardapioUrl = "http://10.51.105.44:8080/pedidoonline-service/cardapio/obter";
    private String savePedidoUrl = "http://10.51.105.44:8080/pedidoonline-service/pedido/novo";
    
    private PedidoClient() {
    	
    }
    
    public static PedidoClient getInstace() {
    	if(instance == null) {
    		instance = new PedidoClient();
    	}
    	return instance;
    }
 
    public Cardapio getCardapio() throws Exception {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpUriRequest req = new HttpGet(cardapioUrl);
        req.addHeader("Accept", "text/json");
        HttpResponse res = client.execute(req);
        InputStream in = res.getEntity().getContent();
        String response = IOUtils.toString(in, "UTF-8");
        
        Gson gson = new Gson();
        Cardapio cardapio = gson.fromJson(response, Cardapio.class);
        return cardapio;
    }
 
    public Pedido newPedido(Pedido pedido) throws Exception {
    	Gson gson = new Gson();
        DefaultHttpClient client = new DefaultHttpClient();
        
        HttpPost request = new HttpPost(savePedidoUrl);
        request.addHeader("content-type", "application/json");
        
        StringEntity se = new StringEntity( gson.toJson(pedido));
        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        
        request.setEntity(se);
        
        HttpResponse response = client.execute(request);
        
        InputStream in = response.getEntity().getContent();
        String responseJson = IOUtils.toString(in, "UTF-8");
        Pedido pedidoResponse = gson.fromJson(responseJson, Pedido.class);
        return pedidoResponse;
    }
    

    public Double getTotalFatura() throws Exception {
    	return 34.23;
    }
    
    public void registrarUrls(String[] urls) {
    	this.cardapioUrl = urls[0].trim();
    	this.savePedidoUrl = urls[1].trim();
    }
}
