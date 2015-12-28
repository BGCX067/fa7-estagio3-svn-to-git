package br.com.eiService.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import br.com.eiService.dao.MarcacaoDao;
import br.com.eiService.dao.UsuarioDao;
import br.com.eiService.infra.DAOFactory;
import br.com.eiService.model.Marcacao;
import br.com.eiService.model.Usuario;

@Path("/resource")
public class Resource {
	
	DAOFactory daoFactory = DAOFactory.getFactory();
	UsuarioDao usuarioDaoO = daoFactory.getUsuariDAO();
	MarcacaoDao marcacaoDao = daoFactory.getMarcacaoDAO();

	@GET 
	@Path("/listUsuarios")
	@Produces("application/json")
	public List<Usuario> exibirUsuarios(){
		//marcacaoDao.beginTransaction();
		
		List<Usuario> users = new ArrayList<Usuario>();
		users = usuarioDaoO.listAll();
		
		usuarioDaoO.commitTransaction();
		return users;
	}
	
	@GET 
	@Path("/listMarcacoes")
	@Produces("application/json")
	public List<Marcacao> exibirMarcacoes(){
		//marcacaoDao.beginTransaction();
		
		List<Marcacao> marcacoes = new ArrayList<Marcacao>();
		marcacoes = marcacaoDao.listAll();
		
		marcacaoDao.commitTransaction();
		return marcacoes;
	}
	
	
	@POST
	@Path("/novaMarcacao")
	@Consumes("application/json")
	public void salvarMarcacao(JSONObject json){
		String descricao =json.optString("descricao");
		double lat = json.optDouble("latitude");
		double lng = json.optDouble("longitude");
		System.out.println(json.toString());
		Marcacao marcacao = new Marcacao();
		marcacao.setDescricao(descricao);
		marcacao.setLatitude(lat);
		marcacao.setLongitude(lng);
		
		marcacaoDao.beginTransaction();
		marcacaoDao.save(marcacao);
		marcacaoDao.commitTransaction();
		
	}
	
	
}
