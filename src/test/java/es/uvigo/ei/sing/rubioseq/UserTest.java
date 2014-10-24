package es.uvigo.ei.sing.rubioseq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	private static EntityManagerFactory emf;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("rubioseq-database");
	}	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if(emf!=null && emf.isOpen()) emf.close();
	}
	@Test
	public void testCreateUser() {
		EntityManager em = emf.createEntityManager();				
		EntityTransaction tx = null;
		try{
			User user = new User();
			user.setUsername("rubioseq");
			user.setPassword("rubioseqpass");
			user.setAdmin(false);
			user.setEmail("rubioseq@rubioseg.org");
			
			tx = em.getTransaction();			
			try{
				tx.begin();		
					em.persist(user);		
				tx.commit();						
			}finally{
				if (tx !=null && tx.isActive()){ tx.rollback(); }
			}
		}finally{	em.close(); 	}
		//check
		try{
			em = emf.createEntityManager();
			tx = em.getTransaction();			
			try{
				tx.begin();		
					//getSingleResult() will throw exception it there is no result
					em.createQuery("SELECT u FROM User u where u.username = 'rubioseq'").getSingleResult();
					@SuppressWarnings("unchecked")
					List<User> users = em.createQuery("SELECT u FROM User u").getResultList();
					for (User u : users){
						System.out.println(u.getUsername());
					}
				tx.commit();						
			}finally{
				if (tx !=null && tx.isActive()){ tx.rollback(); }
			}
		}finally{	em.close();		}			
	}
	
	@Test
	public void testCreateAdminUser() {
		EntityManager em = emf.createEntityManager();				
		EntityTransaction tx = null;
		try{
			User user = new User();
			user.setUsername("rubioseq2");
			user.setPassword("rubioseqpass");
			user.setAdmin(true);
			user.setEmail("rubioseq2@rubioseg.org");
			
			tx = em.getTransaction();			
			try{
				tx.begin();		
					em.persist(user);		
				tx.commit();						
			}finally{
				if (tx !=null && tx.isActive()){ tx.rollback(); }
			}
		}finally{	em.close(); 	}
		//check
		try{
			em = emf.createEntityManager();
			tx = em.getTransaction();			
			try{
				tx.begin();		
					//getSingleResult() will throw exception it there is no result
					em.createQuery("SELECT u FROM User u where u.admin = 'TRUE'").getSingleResult();
				tx.commit();						
			}finally{
				if (tx !=null && tx.isActive()){ tx.rollback(); }
			}
		}finally{	em.close();		}			
	}
}