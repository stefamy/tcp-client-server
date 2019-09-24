# TCP Client and Server Implementation    
TCP client and server implementation using java. Server starts in passive mode and waits for client connection. Once the connection is made, the client can send a string between 1 and 80 characters to the server for simple text formatting (the text is reversed and the capitalization of each letter is inverted). The server returns the formatted text. Once this process is complete, both the client and server close the socket and the programs terminate.

## To run:  

1) Compile server code
   - **cd** into the **tcp-client-server/src/** directory  
   - Type **javac server/ServerApp.java** and hit **return**
   - You should now see two new .class files in the ./server/ directory, as well as a new .class file in both the ./socketdata/ and ./textformatter/ directories.
   
2) Start server
   - In the same command line window:
   - Type **java server/ServerApp.java** and hit **return***  
   - You should now see a message that the TCP server is listening and waiting for a client to connect.
   
3) Compile client code
   - Open another command line window
   - **cd** into the **tcp-client-server/src/** directory  
   - Type **javac client/ClientApp.java** and hit **return***  
   - You should now see two new .class files in the ./client/ directory
   
4) Start client
   - In the same command line window as step 3:
   - Type **java client/ClientApp** and hit **return**   
   - The client should now print a message that it is successfully connected to the server.
   
5) Format string of text
   - The client window should now prompt you for a string to format. Type a **string between 1-80 characters** and hit **return**.
   - The formatted version of your string should now print to the console.
   - At this point, both the server and client connections will close automatically. 

*For both the server and client, you can optionally include two parameters: the port and hostname for the socket. If using this option, both parameters must be supplied, and in the correct order.    
If not provided, or if there is an error with the passed parameters, the program will default to localhost on port 1500.   
*Example valid input:*   
java server/ServerApp 9999 localhost   
java client/ClientApp 9999 localhost   


*Example server output*:  
   
<img alt="server terminal window screenshot" src="https://github.com/stefamy/tcp-client-server/blob/master/server-screenshot.png" width="400">
   
*Example client output*:  
   
<img alt="client terminal window screenshot" src="https://github.com/stefamy/tcp-client-server/blob/master/client-screenshot.png" width="400">
   
