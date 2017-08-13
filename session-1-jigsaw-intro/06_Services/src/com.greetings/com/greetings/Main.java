package com.greetings;

import com.socket.NetworkSocket;

public class Main {
	public static void main(String[] args) {
		NetworkSocket s = NetworkSocket.open();
		System.out.println(s.getClass());
	}
}
