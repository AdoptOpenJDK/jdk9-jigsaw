### Using the TLS Application Layer Protocol negotiation extension (basic form)

In this example we are going to demonstrate how to use the basic form of the ALPN extension introduced in JDK 9. ALPN is used to negotiate the application protocol between the client and the server during TLS handshake.
In the source folder there is a `BasicALPN` project that provides an implementation of a simple TLS client and a TLS server. 

Specify that the client supports the XMPP v1.1 protocol and the server supports the XMPP v1.1 and XMPP v1.2 protocols using the ALPN extension (hint: use the `setApplicationProtocols()` method on the proper instance in the provided implementation).

Use the `compile.sh` script to compile the project:

    $ ./compile.sh

Run the compiled `com.project.Server` class with the `run_server.sh` script:
	    
    $ ./run_server.sh

Run the compiled `com.project.Client` class with the `run_client.sh` script and observe the negotiated protocol (must be XMPP v1.1):

    $ ./run_client.sh
