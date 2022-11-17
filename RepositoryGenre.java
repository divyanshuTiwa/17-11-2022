package com.hibernate.jpa.demo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;

public class RepositoryGenre {
private static EntityManager em;
	
	public RepositoryGenre(final EntityManager em ) {
		this.em = em;
	}
	
	//save genre in the database
	public  void save(final Genre genre) {
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			
			if(!tx.isActive()) {
				tx.begin();
			}
			
			em.persist(genre);
			tx.commit();
			
		}
		catch(HibernateException ex) {
			System.out.println("Inside Save Execution");
			
			throw ex;
			
		}
		finally {		
			
		}	
	}
	// Remove data from the database
	public void remove(Genre[] genres) {
		
	//	List<Genre> genres = findAll();
		
		for(Genre g : genres) {
			EntityTransaction tx = null;
			
			try {
				tx = em.getTransaction();
				
				if(!tx.isActive()) {
					tx.begin();
				}
				
				em.remove(g);
				tx.commit();
				
			}
			catch (Exception e) {

				if(tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			}
		}	
	}
	
	//findAll
	public List<Genre> findAll(){
		
		List<Genre> genres = em.createQuery("from Genre ", Genre.class).getResultList();				

		return genres;
	}
	//findById
	public  Optional<Genre> findById(int id) {
		Genre a = em.find(Genre.class, id);
						
		if(a != null)
			return Optional.of(a);
		else
			return Optional.empty();
	}
	//findAllByName
	public  Optional<Genre> findByName(String name) {
		Genre g= em.createQuery("select m from Genre m where m.name = :name", Genre.class)
						.setParameter("name", name).getSingleResult();
					
		if(g != null)
			return Optional.of(g);
		else
			return Optional.empty();
		}
	
}


