package controllers;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entidades.UsuarioEfika;
import models.LoginServico;
import util.JSFUtil;
import webservices.Usuario;

import java.io.Serializable;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginBean implements Serializable{

	private UsuarioEfika usuario;

	private Usuario usuarioWS;

	private String senha;
	
	private String pagina;
	
	@EJB
	private LoginServico servicoLogin;

	private boolean logado;

	public LoginBean() {
		this.usuario = new UsuarioEfika();
		this.logado = false;
	}

	public void validarLogin() {
				
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (!this.logado){
			
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
					fc.getApplication().getNavigationHandler();
			nav.performNavigation("index.jsf");
		}
		
	}	
	
	public String logar() {

		try {

			this.usuarioWS = this.servicoLogin.buscaLoginWS(this.usuario.getLogin());
			this.servicoLogin.autenticaLogin(this.usuarioWS, this.senha);

			this.logado = true;			
			return "index.jsf"; 

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			this.usuario = new UsuarioEfika();
			return "";

		}

	}

	public void deslogar() {

		this.usuario = new UsuarioEfika();
		this.logado = false;

	}
	
	public void validaPagina(String pagina) {
				
		this.pagina = pagina;
		
	}
	
	public Boolean validaSeAdm() {
		
		Boolean adm = false;
		
		if (this.logado) {
						
			this.usuario = this.servicoLogin.buscaUsuario(this.usuario);
						
			adm = this.usuario.getAdm();
									
		}
		
		return adm;
		
	}
	
	public void redirecionaIndex(Boolean param) {
				
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (!param){
						
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
					fc.getApplication().getNavigationHandler();
			nav.performNavigation("index.jsf");
		}
		
	}	

	public UsuarioEfika getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEfika usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioWS() {
		return usuarioWS;
	}

	public void setUsuarioWS(Usuario usuarioWS) {
		this.usuarioWS = usuarioWS;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
