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

            System.out.println(e.getMessage());

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

    public Votado listarVotoEspecifico(UsuarioEfika usuarioEfika, Dados dados, Celula celula) {

        try {

            Query query = this.entityManager.createQuery("FROM Votado v WHERE v.usuarioEfika =:param1 AND v.dados.celula =:param2");
            query.setParameter("param1", usuarioEfika);
            query.setParameter("param2", celula);
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
