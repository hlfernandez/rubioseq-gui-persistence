package es.uvigo.ei.sing.rubioseq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataStoreTest {
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
		
		try{
			DataStore dataStore = new DataStore();
			dataStore.setPath("/home/hlfernandez/");
			dataStore.setType(DataStoreType.Input);
			dataStore.setMode(DataStoreMode.Shared);
			
			tx = em.getTransaction();			
			try{
				tx.begin();		
					em.persist(dataStore);		
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
					@SuppressWarnings("unchecked")
					List<DataStore> dataStores = (List<DataStore>) em.createQuery("SELECT d FROM DataStore d").getResultList();
					for(DataStore d : dataStores){
						System.out.println(d.getPath());
						em.getTransaction().begin();
						em.remove(d);
						em.getTransaction().commit();
					}
				tx.commit();						
			}finally{
				if (tx !=null && tx.isActive()){ tx.rollback(); }
			}
		}finally{	em.close();		}			
	}
}