package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Celula;
import entidades.Dados;
import entidades.Votado;
import models.VotadoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class VotadoBean {
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;
	
	private Votado votado;
	
	@EJB
	private VotadoServico votadoServico;
	
	public VotadoBean() {

		this.votado = new Votado();
		
	}
	
	public void votar(Dados dados, Celula celula) {
		
		System.out.println("Nome dado: " + dados.getNome());
		System.out.println("Nome celula: " + celula.getNome());
		
		
		
	}
	
	public void teste() {
		
		System.out.println("entrou teste");
		
	}
	
	public List<Votado> listarVotados(Dados dados) {
				
		return this.votadoServico.listaVotado(dados);
		
	}

	public Votado getVotado() {
		return votado;
	}

	public void setVotado(Votado votado) {
		this.votado = votado;
	}

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}	

}
