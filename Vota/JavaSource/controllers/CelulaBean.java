package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.Celula;
import models.CelulaServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class CelulaBean {
	
	private Celula celula;
	
	private Celula celulaModifica;
	
	@EJB
	private CelulaServico celulaServico;
		
	public CelulaBean() {

		this.celula = new Celula();
		
		this.celulaModifica = new Celula();
		
	}
	
	public void	cadastrarCelula() {
		
		try {
			
			this.celulaServico.cadastrarCelula(this.celula);
			
			JSFUtil.addInfoMessage("Celula cadastrado com sucesso.");
			
			this.celula = new Celula();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public void modificarCelula() {
		
		try {
			
			this.celulaServico.modificarCelula(this.celulaModifica);
			
			JSFUtil.addInfoMessage("Celula modificado com sucesso.");
			
			this.celulaModifica = new Celula();
			
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public List<Celula> listarTodasCelulas() {
		
		return this.celulaServico.listarTodasCelulas();
		
	}
	
	public List<Celula> listarCelulaAtivo() {
		
		return this.celulaServico.listarCelulaAtivo();
		
	}

	public Celula getCelula() {
		return celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
	}

	public Celula getCelulaModifica() {
		return celulaModifica;
	}

	public void setCelulaModifica(Celula celulaModifica) {
		this.celulaModifica = celulaModifica;
	}	
	
}
