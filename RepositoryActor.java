package com.hibernate.jpa.demo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RepositoryActor {
	
private static EntityManager em;
	
	public RepositoryActor(final EntityManager em) {
		this.em=em;
	}
	
	//7.Saving objects of type Actor to the database
	public void save(final Actor actor) {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			
			if(!tx.isActive()) {
				tx.begin();
			}
			
			em.persist(actor);
			tx.commit();
			
		} catch (Exception e) {

			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}
	
	//8.Look for objects in the database of type Actor by id
	public Optional<Actor> findById(Integer id) {
		
		Actor a = em.find(Actor.class, id);
		
		
		if(a!= null)
			return Optional.of(a);
		else
			return Optional.empty();
		
	}
	
	//search all actors
	public List<Actor> findAllActors() {
		return em.createQuery("from actors").getResultList();
	}
	
	/*9.	search for objects in the Actor type database that were born after a certain year 
	/(i.e. the year is a method parameter)*/
	
	public Optional<Actor> findAllBornByYear(Integer year) {
		Actor a = em.createQuery("select a from actors a where a.year_of_birth > :year_of_birth", Actor.class)
					.setParameter("Year", year).getSingleResult();
		
		
		if(a != null)
			return Optional.of(a);
		else
			return Optional.empty();
		
	}
	
	/*10.	look for objects in the database of the Actor type, the names of which end with the
	 specified value of the String type object*/
		public List<Actor> findAllWithLastNameEndsWith(final String lastname) {
			List<Actor> actors = em.createQuery("select a FROM actors a WHERE a.lastName LIKE :lastName", Actor.class)
		        .setParameter("lastName", "%" + lastname)
		        .getResultList();
			return actors;	
	
	}
}
	


