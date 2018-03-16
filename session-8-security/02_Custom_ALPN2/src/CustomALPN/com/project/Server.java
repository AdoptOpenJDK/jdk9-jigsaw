package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSession;

public class Server {

	public static void main(String[] args) throws IOException{
		
		System.setProperty("javax.net.ssl.keyStore", "C:/Users/Martin/sample.pfx");
		System.setProperty("javax.net.ssl.keyStorePassword", "sample");
		
		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
	    ServerSocket ss = ssf.createServerSocket(4444);
	    while (true) {
	      SSLSocket s = (SSLSocket) ss.accept();
	      
	      s.setHandshakeApplicationProtocolSelector((serverSocket, clientProtocols) -> {
				SSLSession handshakeSession = serverSocket.getHandshakeSession();
				String cipher = handshakeSession.getCipherSuite();
				int packetBufferSize = handshakeSession.getPacketBufferSize();
				if("RC4".equals(cipher) && packetBufferSize > 1024) {
					return "protocol1";
				} else {
					return "protocol2";
				}
		  });
	      
	      SSLParameters params = s.getSSLParameters();
	      s.setSSLParameters(params);
	      
	      BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	      String line = null;
	      PrintStream out = new PrintStream(s.getOutputStream());
	      while (((line = in.readLine()) != null)) {
	        System.out.println(line);
		    out.println("Hi, client");
	      }
	      in.close();
	      out.close();
	      s.close();
	    }
	    
	}
}
