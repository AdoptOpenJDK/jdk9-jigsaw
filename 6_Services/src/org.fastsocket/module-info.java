module org.fastsocket {
	requires com.socket;
	provides com.socket.spi.NetworkSocketProvider
		with org.fastsocket.FastNetworkSocketProvider;
}