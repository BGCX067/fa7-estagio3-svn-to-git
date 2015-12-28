package service;

import java.util.Date;
import java.util.List;

import pojo.Chamado;
import pojo.Equipamento;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ApiService {

	@GET("/api/chamados/chamadossuporte")
	public void buscarTodosChamadosDisponiveis(Callback<List<Chamado>> chamados);

	@GET("/api/chamados/equipamento/{serial}")
	public Equipamento consultarEquipamento(@Path("serial") String serial);

	@POST("/api/chamados/chamadosresponsavel/{responsavel}")
	public void chamadosresponsavel(@Path("id") int id);

	@POST("/api/chamados/attResponsavel")
	public void finalizarChamado(@Path("id")int id, @Path("textoRetorno") String textoRetorno, @Path("dataFechamento") Date dataFechamento, @Path("status") String status);
}
