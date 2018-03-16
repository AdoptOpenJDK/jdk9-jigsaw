### Using the TLS Application Layer Protocol negotiation extension (custom form)

In this example we are going to demonstrate how to use the custom form of the ALPN extension introduced in JDK 9. ALPN is used to negotiate the application protocol between the client and the server during TLS handshake.
In the source folder there is a `CustomALPN` project that provides an implementation of a simple TLS client and a TLS server. 

Specify using the ALPN extension that the client supports the protocol1 and protocol2 protocols and the server supports the protocol1 protocol only if the negotiated cryptographic cipher is RC4 and the packet buffer size is bigger than 1024 bytes  (hint: use the `setApplicationProtocols()` method on the proper instance in the provided implementation on the client and the `setHandshakeApplicationProtocolSelector()` method on the proper instance on the server).

Use the `compile.sh` script to compile the project:

    $ ./compile.sh

Run the compiled `com.project.Server` class with the `run_server.sh` script:
	    
    $ ./run_server.sh

Run the compiled `com.project.Client` class with the `run_client.sh` script and observe the negotiated protocol (must be protocol2):

    $ ./run_client.sh
