package at.jku.csi.producer;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import at.jku.csi.cdi.Service;

@Service
public class EntityManagerProducer implements Serializable{

	@PersistenceUnit(unitName = "csiSA")
	private EntityManagerFactory entityManagerFactory;

	@Produces
	@RequestScoped
	public EntityManager create() {
		return entityManagerFactory.createEntityManager();
	}

	public void dispose(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}
}