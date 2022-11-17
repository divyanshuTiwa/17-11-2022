package com.hibernate.jpa.demo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;

public class RepositoryMovie {
	
private static EntityManager em;
	
	public RepositoryMovie(final EntityManager em) {
		this.em = em;
	}
	
	//11.	adding Movie records to the database
	public  void save(final Movie movie) {
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			
			if(!tx.isActive()) {
				tx.begin();
			}
			
			em.persist(movie);
			tx.commit();
			
		}
		catch(HibernateException ex) {
			System.out.println("Inside Save--exce");
			
			throw ex;
			
		}
		finally {		
			
		}	
	}
	

	public Optional<Movie> findById(Integer id) {
		Movie m = em.find(Movie.class, id);
		
		
		if(m!= null)
			return Optional.of(m);
		else
			return Optional.empty();
	}
	
	public List<Movie> findAll() {
		return em.createQuery("from movies").getResultList();
	}
	
	public Optional<Movie> findByTitle(String title) {
		Movie m = em.createQuery("select m from movies m where m.title= :title", Movie.class)
					.setParameter("Movie_title", title).getSingleResult();
		
		
		if(m != null)
			return Optional.of(m);
		else
			return Optional.empty();
		
	}
	//15.	removing one Movie record from the database.
		public void remove(final Movie movie) {
			EntityTransaction tx = null;
			
			try {
				tx = em.getTransaction();
				
				if(!tx.isActive()) {
					tx.begin();
				}
				
				em.remove(movie);
				tx.commit();
				
			} 
			catch (Exception e) {

				if(tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			}
		}
		
		//16.	removing all Movie records from the database
		public void remove() {
			
			List<Movie> movies = findAll();
			
			for(Movie m : movies) {
				EntityTransaction tx = null;
				
				try {
					tx = em.getTransaction();
					
					if(!tx.isActive()) {
						tx.begin();
					}
					
					em.remove(m);
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


}
