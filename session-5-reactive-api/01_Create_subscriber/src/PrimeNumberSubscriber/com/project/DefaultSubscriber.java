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
		boolean prime = true;
		for(int check = 2; check <= number/2; check++) {
			if(number % check == 0) {
				prime = false;
				break;
			}
		}
		
		if(prime) {
			LOGGER.info(number + " is prime !");
		} else {
			LOGGER.info(number + " is not prime !");
		}

		subscription.request(1);
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}
}
