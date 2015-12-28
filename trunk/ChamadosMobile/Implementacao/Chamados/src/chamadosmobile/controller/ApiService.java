package chamadosmobile.controller;

import java.util.List;

import pojo.Chamado;
import retrofit.Callback;
import retrofit.http.GET;

public interface ApiService {

	@GET("/api/chamados/chamadossuporte")
	public void buscarTodosChamadosDisponiveis(Callback<List<Chamado>> x);
}
