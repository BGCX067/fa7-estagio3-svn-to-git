package br.com.eiService.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONObject;
import org.jasypt.util.text.BasicTextEncryptor;

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
	@Path("/getUsuario/{email}")
	@Produces("application/json")
	public Usuario buscarUsuario(@PathParam("email") String email){
		usuarioDaoO.beginTransaction();
		Usuario usuario = usuarioDaoO.buscarPorEmail(email);
		usuarioDaoO.commitTransaction();
		
		return usuario;
	}
	
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
	
	@POST
	@Path("/novoUsuario")
	@Consumes("application/json")
	public void salvarUsuario(JSONObject json){
		//MessageDigest.getInstance("MD5");
		String email =json.optString("email");
		String senha = json.optString("senha");
		String nome = json.optString("nome");
		//BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		//textEncryptor.setPassword("senha");
		//String senhaEncrypted = textEncryptor.encrypt(senha);
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuarioDaoO.beginTransaction();
		usuarioDaoO.save(usuario);
		usuarioDaoO.commitTransaction();
		
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
		int tipoMarcacao = json.optInt("tipoMarcacao");
		System.out.println(json.toString());
		Marcacao marcacao = new Marcacao();
		marcacao.setDescricao(descricao);
		marcacao.setLatitude(lat);
		marcacao.setLongitude(lng);
		marcacao.setTipoMarcacao(tipoMarcacao);
		marcacaoDao.beginTransaction();
		marcacaoDao.save(marcacao);
		marcacaoDao.commitTransaction();

	}
	
	
}
