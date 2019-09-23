package server;

import java.net.Socket;
import socketdata.SocketData;
import textformatter.TextFormatter;

/**
 * TCP Server Program.
 *
 * Opens a server, accepts a single client, accepts a single string from the client, formats the
 * string, returns the new string to the client, and terminates.
 */
public class ServerApp {

  private static final int NUM_OPTIONAL_ARGS = 2;

  /**
   * Runs through the following steps using the passed server, terminating the program once
   * complete: - Accepts a single client through the passed port, - Receives a string from the
   * client, - Formats and sends the new string to the client, - Ends the program.
   *
   * @param server - The server to run the program on.
   */
  public static void run(Server server) {
    server.listen();
    System.out.println("Server listening. Waiting for client to connect...");
    Socket clientSocket = null;
    while (clientSocket == null) {
      clientSocket = server.acceptClient();
    }
    System.out.println("Client connected. Handling requests...");
    String clientInput = server.receive(clientSocket);
    if (clientInput == null) {
      server.end(1);
    } else {
      String formattedString = TextFormatter.reverseChangeCase(clientInput);
      server.send(clientSocket, formattedString);
      System.out.println("Client request complete. Closing socket and terminating program.");
      server.end(0);
    }
  }

  /**
   * Main program class. Can optionally pass command lne arg with port number and host address to
   * bind to. If one or both are missing, defaults to port 1500 on localhost address.
   *
   * @param args - Command line arguments. Optionally, can supply two args: args[0]: Port number
   * args[1]: Host address to bind server to If one or both are missing or invalid, defaults to port
   * number 1500 on localhost address.
   */
  public static void main(String[] args) {
    // Set up socket data:
    SocketData socketData = new SocketData();

    // If optional args supplied, process the data:
    if (args.length == NUM_OPTIONAL_ARGS) {
      socketData.setPort(args[0]);
      socketData.setAddress(args[1]);
    }

    // Run the program:
    ServerApp.run(new Server(socketData));
  }
}
