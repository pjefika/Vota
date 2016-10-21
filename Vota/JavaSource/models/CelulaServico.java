package models;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Celula;

@Stateless
public class CelulaServico {
	
	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;
	
	public void cadastrarCelula(Celula celula) throws Exception {
		
		try {
			
			this.entityManager.persist(celula);
			
		} catch (Exception e) {

			throw new Exception("Erro ao cadastrar Celula");
			
		}
		
	}
	
	public void modificarCelula(Celula celula) throws Exception {
		
		try {
			
			this.entityManager.merge(celula);
			
		} catch (Exception e) {

			throw new Exception("Erro ao modificar Celula");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Celula> listarTodasCelulas(){ 
		
		try {
			
			Query query = this.entityManager.createQuery("FROM Celula c");
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<Celula>();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Celula> listarCelulaAtivo() {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM Celula c WHERE c.ativo =:param1");
			query.setParameter("param1", true);
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<Celula>();
			
		}
		
	}

}
