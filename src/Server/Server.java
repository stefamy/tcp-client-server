package server;

import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import socketdata.SocketData;

/**
 * TCP Server (single client) implementation with associate methods to send and receive data.
 */
public class Server {

  private SocketData socketData;
  private ServerSocket serverSocket;

  /**
   * Creates a new TCP server object for the passed port and IP address.
   *
   * @param socketData - Port number and address to use for the socket, in a SocketData object.
   */
  public Server(SocketData socketData) {
    this.socketData = socketData;
    this.serverSocket = null;
  }

  /**
   * Opens a server socket in passive mode to await client connection. If unable to open socket,
   * prints appropriate error message and exits.
   */
  public void listen() {
    try {
      this.serverSocket = new ServerSocket(this.socketData.getPort(), 0,
          this.socketData.getAddress());
    } catch (IllegalArgumentException e) {
      System.out.println(String.format("Port number %s is out of valid range. Unable to open "
          + "socket. Exiting.", this.socketData.getPort()));
      exit(1);
    } catch (IOException e) {
      System.out.println(String.format("I/O error prevented opening server socket on port %s. "
          + "Exiting.", this.socketData.getPort()));
      exit(1);
    }
  }

  /**
   * Accepts a client connection and returns the client socket. In case of I/O exception, prints
   * appropriate error message and returns null.
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
   * Sends the passed string to the passed client socket. If exceptions encountered, prints
   * appropriate error messages.
   *
   * @param clientSocket - Client socket to send output to.
   * @param outputText - Message to send through socket, as a String.
   */
  public void send(Socket clientSocket, String outputText) {
    try {
      PrintWriter outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
      outputStream.println(outputText);
    } catch (IOException e) {
      System.out.println("Error creating output stream.");
    }
  }

  /**
   * Reads the data from the passed socket and returns it as a String. If exceptions encountered,
   * prints appropriate error messages and returns null.
   *
   * @param clientSocket - Client socket to send output to.
   * @return Message received from client socket, as a String.
   */
  public String receive(Socket clientSocket) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      return in.readLine();
    } catch (IOException e) {
      System.out.println("Error reading from input stream. Exiting.");
    }
    return null;
  }

  /**
   * Closes this socket and exits the program. If exception encountered when closing socket, prints
   * appropriate error message and exits with status 1.
   *
   * @param status - Status to exit with (0 for success, 1 for errors), as an int.
   */
  public void end(int status) {
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      System.out.println("I/O error prevented closing server socket. Exiting.");
      exit(1);
    }
    exit(status);
  }

}
