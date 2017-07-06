package barbearia.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import barbearia.entity.Comentario;
import barbearia.util.JPAUtil;

public class ComentarioDAO {

	EntityManager manager;

	public ComentarioDAO() {
		manager = new JPAUtil().getEntityManager();
	}
	
	public void save(Comentario c) {
		manager.getTransaction().begin();
		manager.persist(c);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public void update(Comentario c) {
		manager.getTransaction().begin();
		manager.merge(c);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public void delete(Comentario c) {
		manager.getTransaction().begin();
		manager.remove(manager.find(c.getClass(), c.getId()));
		manager.getTransaction().commit();
		manager.close();
	}
	
	public Comentario findById(Class<Comentario> comentario, Integer id) {
		manager.getTransaction().begin();
		Comentario post = manager.find(comentario, id);
		manager.close();
		return post;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comentario> findByLogin(String nome) {
		manager.getTransaction().begin();
		Query query = manager
                .createQuery("select c from Comentario c where c.nome like :pName");
		query.setParameter("pName", "%" + nome + "%");
		List<Comentario> comentarios = query.getResultList();
		manager.close();
		return comentarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comentario> findByAtivo() {
		manager.getTransaction().begin();
		Query query = manager
                .createQuery("select c from Comentario c where c.nome like :pActive");
		query.setParameter("pActive", true);
		List<Comentario> comentarios = query.getResultList();
		manager.close();
		return comentarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comentario> findAll() {
		manager.getTransaction().begin();
		Query query = manager
                .createQuery("select c from Comentario c");
		List<Comentario> comentarios = query.getResultList();
		manager.close();
		return comentarios;
	}
}
