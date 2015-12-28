package chamadosmobile.controller;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestClient {

	private static final String BASE_URL = "http://portal.blcm.cialne.com.br:8787/chamados_mobile";
	private ApiService apiService;

	public RestClient()
	{
		Gson gson = new GsonBuilder()
		.setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
		.create();

		RestAdapter restAdapter = new RestAdapter.Builder()
		.setLogLevel(RestAdapter.LogLevel.FULL)
		.setEndpoint(BASE_URL)
		.setConverter(new GsonConverter(gson))
		.build();

		apiService = restAdapter.create(ApiService.class);
	}

	public ApiService getApiService()
	{
		return apiService;
	}
}
