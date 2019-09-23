package server;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP Server Program.
 *
 * Opens a server, accepts a single client, accepts a single string from the client, formats the
 * string, returns the new string to the client, and terminates.
 */
public class ServerApp {

  private static final int NUM_OPTIONAL_ARGS = 2;
  private static final int DEFAULT_PORT_NUMBER = 1500;
  private static final InetAddress DEFAULT_LOCALHOST_ADDR = InetAddress.getLoopbackAddress();

  /**
   * Runs the program through the following steps using the passed port, terminating the program
   * once complete:
   * - Accepts a single client through the passed port,
   * - Receives a string from the client,
   * - Formats and sends the new string to the client,
   * - Ends the program.
   *
   * @param server - The server to run the program on.
   */
  public static void run(Server server) {
    server.listen();
    System.out.println("Server listening. Waiting for client to connect...");
    Socket clientSocket = server.acceptClient();
    System.out.println("Client connected. Handling requests...");
    server.handleClient(clientSocket);
    System.out.println("Client request complete. Closing socket and terminating program.");
    server.end();
  }

  /**
   * Main program class. Can optionally pass command lne arg with port number and host address to
   * bind to. If one or both are missing, defaults to port 1500 on localhost address.
   *
   * @param args - Command line arguments. Optionally, can supply two args:
   *     args[0]: Port number
   *     args[1]: Host address to bind server to
   *     If one or both are missing or invalid, defaults to port number 1500 on localhost address.
   */
  public static void main(String[] args) {
    // Set up our defaults:
    int port = DEFAULT_PORT_NUMBER;
    InetAddress address = DEFAULT_LOCALHOST_ADDR;

    // Check if args are passed, and if so, are valid:
    if (args.length == NUM_OPTIONAL_ARGS) {
      try {
        port = Integer.valueOf(args[0]);
        try {
          address = InetAddress.getByName(args[1]);
        } catch (UnknownHostException e) {
          System.out.println("An unknown host was passed. Defaulting to local loopback address.");
        }
      } catch (NumberFormatException e) {
        System.out.println("An invalid port number was passed. Defaulting to port 1500.");
      }
    }

    // Run the program:
    ServerApp.run(new Server(port, address));
  }
}
