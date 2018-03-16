package com.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Client {

	private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
	
	public static void main(String[] args) throws InterruptedException {
		
		try {
			System.setProperty("javax.net.ssl.trustStore", "C:/Users/Martin/sample.pfx");
			System.setProperty("javax.net.ssl.trustStorePassword", "sample");

			SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket s = (SSLSocket) ssf.createSocket("127.0.0.1", 4444);
			SSLParameters params = s.getSSLParameters();
			params.setApplicationProtocols(new String[] {"XMPP v1.1"});
			s.setSSLParameters(params);
			
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("Hi, server.");
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String x = in.readLine();
			System.out.println(x);
			System.out.println("Used protocol: " + s.getApplicationProtocol());
			
			out.close();
			in.close();
			s.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
