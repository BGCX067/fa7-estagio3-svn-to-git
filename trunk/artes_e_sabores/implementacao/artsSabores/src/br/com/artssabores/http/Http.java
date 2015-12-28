package br.com.artssabores.http;

import java.util.Map;

public abstract class Http {
	// utiliza url connection - nativa do java
	public static final int NORMAL = 1;
	// utiliza o Jakarta HttpClient - nativa do android
	public static final int JAKARTA = 2;

	public static Http getIntance(int tipo) {
		switch (tipo) {
		case NORMAL:
			// UrlConnection
			return new HttpNormal();
		case JAKARTA:
			// Jakarta Commons HttpClient
			return new HttpJakarta();
		default:
			return new HttpNormal();
		}
	}
	// Rerona o texto do arquivo
	public abstract String downloadArquivo(String url);
	// retorna o bytes da imagen
	//public abstract byte[] downloadImagen(String url);
	//faz post enviando por parametros
	//public abstract String doPost(String url, Map map);
}
