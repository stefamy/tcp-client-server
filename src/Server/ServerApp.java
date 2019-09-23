package server;

import java.net.Socket;

/**
 * TCP Server Program.
 *
 * Opens a server, accepts a single client, accepts a single string from the client, formats the
 * string, returns the new string to the client, and terminates.
 */
public class ServerApp {

  private static final int DEFAULT_PORT_NUMBER = 1500;

  /**
   * Runs the program through the following steps using the passed port, terminating the program
   * once complete:
   * - Accepts a single client through the passed port,
   * - Receives a string from the client,
   * - Formats and sends the new string to the client,
   * - Ends the program.
   */
  public static void run(int port) {
    Server server = new Server(port);
    server.listen();
    System.out.println("Server listening. Waiting for client to connect...");
    Socket clientSocket = server.acceptClient();
    System.out.println("Client connected. Handling requests...");
    server.handleClient(clientSocket);
    System.out.println("Client request complete. Closing socket and terminating program.");
    server.end();
  }

  /**
   * Main program class. Can optionally pass command lne arg with port number to use. If none
   * supplied, defaults to port 1500.
   *
   * @param args - Command line arguments. Optionally, can supply a single arg with the port number
   * to use for server socket (number range 0-65535, inclusive).
   */
  public static void main(String[] args) {
    int port = DEFAULT_PORT_NUMBER;
    if (args.length > 0) {
      try {
        port = Integer.valueOf(args[0]);
      } catch (NumberFormatException e) {
        System.out.println("An invalid port number was passed. Defaulting to port 1500.");
      }
    }
    ServerApp.run(port);
  }
}
