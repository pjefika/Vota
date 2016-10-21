package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Celula;
import entidades.Dados;
import entidades.UsuarioEfika;
import entidades.Votado;

@Stateless
public class VotadoServico {

	@PersistenceContext(unitName = "vu")
	private EntityManager entityManager;

	public void votar(Dados dados, UsuarioEfika usuarioEfika) throws Exception {

		try {
			
			Votado votado = new Votado();			
			Date date = new Date();			
			
			votado.setDataDoVoto(date);
			votado.setDados(dados);
			votado.setUsuarioEfika(usuarioEfika);

			this.entityManager.persist(votado);

		} catch (Exception e) {

			throw new Exception("Erro ao Votar");

		}

	}

	@SuppressWarnings("unchecked")
	public List<Votado> listarVotos(Dados dados) {

		try {

			Query query = this.entityManager.createQuery("FROM Votado v WHERE v.dados =:param1");
			query.setParameter("param1", dados);
			return query.getResultList();

		} catch (Exception e) {

			return new ArrayList<Votado>();

		}

	}

	public Votado listarVotoEspecifico(UsuarioEfika usuarioEfika, Celula celula , Dados dados) {

		try {

			Query query = this.entityManager.createQuery("FROM Votado v WHERE v.usuarioEfika =:param1 AND v.dados.celula =:param3 AND v.dados.evento =:param4");
			query.setParameter("param1", usuarioEfika);
			query.setParameter("param3", celula);
			query.setParameter("param4", dados.getEvento());
			return (Votado) query.getSingleResult();

		} catch (Exception e) {

			return new Votado();

		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Votado> listaVotado(Dados dados) {
		
		try {
			
			Query query = this.entityManager.createQuery("FROM Votado v WHERE v.dados =:param1");
			query.setParameter("param1", dados);
			return query.getResultList();
			
		} catch (Exception e) {

			return new ArrayList<Votado>();
			
		}
		
	}

}
