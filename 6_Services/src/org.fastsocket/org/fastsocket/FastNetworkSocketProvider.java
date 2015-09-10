package org.fastsocket;

import com.socket.NetworkSocket;

public class FastNetworkSocketProvider extends com.socket.spi.NetworkSocketProvider {
    public FastNetworkSocketProvider() {}

    @Override
    public NetworkSocket openNetworkSocket() {
        return new FastNetworkSocket();
    }
}
