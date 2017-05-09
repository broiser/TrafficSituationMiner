package at.jku.csi.producer;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import at.jku.csi.cdi.Service;

@Service
public class ExecutorServiceProducer implements Serializable {

	@Produces
	@RequestScoped
	public ExecutorService create() {
		return Executors.newCachedThreadPool();
	}

	public void dispose(@Disposes ExecutorService executorService) {
		if (executorService.isShutdown()) {
			executorService.shutdownNow();
		}
	}
}