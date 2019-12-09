package jmsapp.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jmsapp.modelo.Produto;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CarrinhoEjb {

	@PersistenceContext(unitName = "jmsapp")
	private EntityManager em;

	public Produto add(Produto produto) {
		em.persist(produto);
		em.flush();
		return produto;
	}
	
	public List<Produto> findAll() {
		
		return em.createQuery("SELECT c FROM Produto c").getResultList();
	}
	
	public Produto findOne(Integer id) {
		return em.find(Produto.class, id);
	}

	public void remove(Integer id) {
		em.remove(this.findOne(id));	
	}

	public Produto adicionar(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
