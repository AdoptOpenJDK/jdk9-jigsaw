package com.socket.spi;

import com.socket.NetworkSocket;

public abstract class NetworkSocketProvider {
    protected NetworkSocketProvider() {}

    public abstract NetworkSocket openNetworkSocket();
}
