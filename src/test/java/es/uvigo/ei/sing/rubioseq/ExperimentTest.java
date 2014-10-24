package es.uvigo.ei.sing.rubioseq;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExperimentTest {
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
	public void testCreateExperiment() {
		EntityManager em = emf.createEntityManager();				
		EntityTransaction tx = null;
		
		// First, create an user who will own the experiment
		User user = new User();
		try{
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
		}finally{	}
		
		try{
			Experiment experiment = new Experiment();
			experiment.setUser(user);
			experiment.setStatus(ExperimentStatus.Created);
			experiment.setType(ExperimentType.SNV);
			experiment.setExecutionLevel(0);
			experiment.setWorkingDirectory("/home/rubioseq/data");
			
			tx = em.getTransaction();			
			try{
				tx.begin();		
					em.persist(experiment);		
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
					em.createQuery("SELECT e FROM Experiment e where e.user = 'rubioseq'").getSingleResult();
				tx.commit();						
			}finally{
				if (tx !=null && tx.isActive()){ tx.rollback(); }
			}
		}finally{	em.close();		}			
	}
}