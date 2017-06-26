package models;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Evento;
import entidades.UsuarioEfika;

@Stateless
public class EventoServico {

    @PersistenceContext(unitName = "vu")
    private EntityManager entityManager;

    public void criaEvento(Evento evento, UsuarioEfika usuarioEfika) throws Exception {

        try {

            evento.setUsuarioEfika(usuarioEfika);

            this.entityManager.persist(evento);

        } catch (Exception e) {

            throw new Exception("Erro ao cadastrar");

        }

    }

    @SuppressWarnings("unchecked")
    public List<Evento> listarEvento() {

        try {

            Query query = this.entityManager.createQuery("FROM Evento e WHERE e.ativo =:param1");
            query.setParameter("param1", true);

            return query.getResultList();

        } catch (Exception e) {

            return new ArrayList<Evento>();

        }

    }

    @SuppressWarnings("unchecked")
    public List<Evento> listarTodosEventos() {

        try {

            Query query = this.entityManager.createQuery("FROM Evento e");

            return query.getResultList();

        } catch (Exception e) {

            return new ArrayList<Evento>();

        }

    }

    public Evento listarEventoEspecifico(Evento evento) {

        try {

            Query query = this.entityManager.createQuery("FROM Evento e WHERE e.id =:param1");
            query.setParameter("param1", evento.getId());

            return (Evento) query.getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    public void modificarEvento(Evento evento) throws Exception {

        try {

            this.entityManager.merge(evento);

        } catch (Exception e) {

            throw new Exception("Erro ao modificar Evento");

        }

    }

}
