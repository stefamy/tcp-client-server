package server;

import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP Server (single client) implementation with required ServerApp program methods.
 */
public class Server {

  private int port;
  private ServerSocket serverSocket;

  /**
   * Creates a new TCP server object for the passed port.
   *
   * @param port - Port number to create server socket on, as an int.
   */
  public Server(int port) {
    this.port = port;
    this.serverSocket = null;
  }


  /**
   * Opens a server socket in passive mode to await client connection. If unable to open socket,
   * prints appropriate error message and exits.
   */
  public void listen() {
    try {
      this.serverSocket = new ServerSocket(this.port);
    }
    catch (IllegalArgumentException e) {
      System.out.println(String.format("Port number %s is out of valid range. Unable to open "
          + "socket. Exiting.", this.port));
      exit(1);
    }
    catch (IOException e) {
      System.out.println(String.format("I/O error prevented opening server socket on port %s. "
          + "Exiting.", this.port));
      exit(1);
    }
  }

  /**
   * Accepts a client connection and returns the client socket. In case of I/O exception,
   * prints appropriate error message and returns null.
   *
   * @return Connected client socket or null if connection fails.
   */
  public Socket acceptClient() {
    try {
      return this.serverSocket.accept();
    } catch (IOException e) {
      System.out.println("I/O error prevented accepting connection with client.");
    }
    return null;
  }

  /**
   * Given a client socket, accepts the string to format, runs formatter, returns result to client.
   * If exceptions encountered, prints appropriate error messages and continues.
   *
   * @param clientSocket - Client socket to receive input and send output through.
   */
  public void handleClient(Socket clientSocket) {
    try {
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) == null) {
          out.println("Received empty message. Send requested string for formatting.");
        }
        String reversedStr = StringFormatter.reverseChangeCase(inputLine);
        out.println(reversedStr);
      } catch (IOException e) {
        System.out.println("Error reading from input stream.");
      }
    } catch (IOException e) {
      System.out.println("Error creating output stream.");
    }
  }

  /**
   * Closes this socket and exits the program. If exception encountered when closing socket,
   * prints appropriate error message and exits with status 1.
   */
  public void end() {
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      System.out.println("I/O error prevented closing server socket. Exiting.");
      exit(1);
    }
    exit(0);
  }

  }