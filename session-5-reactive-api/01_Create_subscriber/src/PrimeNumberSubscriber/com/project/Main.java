package com.project;

import java.util.Arrays;
import java.util.concurrent.SubmissionPublisher;
import java.util.logging.Logger;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) throws InterruptedException {

			LOGGER.info("Checking prime numbers ...");
		    SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();  
		    
		    DefaultSubscriber subscriber = new DefaultSubscriber();  
		    publisher.subscribe(subscriber); 
		    Integer[] numbers = {4, 17, 19, 101, 105, 107, 201};  
		    
		    Arrays.asList(numbers).stream().forEach(i -> publisher.submit(i));
		    
		    // add some wait time ...
		    Thread.sleep(3000);
		    publisher.close();
	}
}
