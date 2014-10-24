package es.uvigo.ei.sing.rubioseq;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RUbioSeqConfigurationTest {
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
	public void testCreateConfiguration() {
		EntityManager em = emf.createEntityManager();				
		EntityTransaction tx = null;
		try{
			RUbioSeqConfiguration config = new RUbioSeqConfiguration();
			config.setRubioseqCommand("/opt/rubioseq3/rubioseq.pl");
			
			tx = em.getTransaction();			
			try{
				tx.begin();		
					em.persist(config);		
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
					em.createQuery("SELECT r FROM RUbioSeqConfiguration");
				tx.commit();						
			}finally{
				if (tx !=null && tx.isActive()){ tx.rollback(); }
			}
		}finally{	em.close();		}			
	}
}