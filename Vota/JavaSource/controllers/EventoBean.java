package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Evento;
import models.EventoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class EventoBean {

	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;

	private Evento evento;

	private Evento eventoModifica;

	private List<Evento> eventosFiltrados;

	@EJB
	private EventoServico eventoServico;

	public EventoBean() {

		this.evento = new Evento();

		this.eventoModifica = new Evento();
		
	}

	public void cadastrarEvento() {

		try {

			this.eventoServico.criaEvento(this.evento, this.sessao.getUsuario());

			this.evento = new Evento();

			JSFUtil.addInfoMessage("Evento cadastrado com sucesso.");

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public List<Evento> listarEvento() {

		return this.eventoServico.listarEvento();

	}

	public List<Evento> listarTodosEventos() {
		
		this.eventosFiltrados = this.eventoServico.listarTodosEventos();

		return this.eventosFiltrados;

	}

	public void modificarEvento() {

		try {

			this.eventoServico.modificarEvento(this.eventoModifica);
			
			JSFUtil.addInfoMessage("Evento modificado com sucesso.");

			this.eventoModifica = new Evento();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void listarEventoEspecifico() {

		this.evento = this.eventoServico.listarEventoEspecifico(this.evento);

	}	
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}	

	public List<Evento> getEventosFiltrados() {
		return eventosFiltrados;
	}

	public void setEventosFiltrados(List<Evento> eventosFiltrados) {
		this.eventosFiltrados = eventosFiltrados;
	}

	public Evento getEventoModifica() {
		return eventoModifica;
	}

	public void setEventoModifica(Evento eventoModifica) {
		this.eventoModifica = eventoModifica;
	}		

}
