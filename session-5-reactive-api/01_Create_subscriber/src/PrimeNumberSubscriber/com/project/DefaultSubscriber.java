package com.project;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultSubscriber implements Subscriber<Integer>{

	private static final Logger LOGGER = Logger.getLogger(DefaultSubscriber.class.getName());
	
	private Subscription subscription;
	
	@Override
	public void onComplete() {
		LOGGER.info("COMPLETED");
	}

	@Override
	public void onError(Throwable t) {
		LOGGER.log(Level.SEVERE, t.getMessage(), t);
	}
	
	@Override
	public void onNext(Integer number) {
		// TODO
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		// TODO
	}
}
