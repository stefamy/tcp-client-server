package client;

import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import socketdata.SocketData;

/**
 * TCP Client implementation with associate methods to send and receive data.
 */
public class Client {

  private SocketData socketData;
  private Socket socket;

  /**
   * Constructs a new Client object with the given SocketData.
   *
   * @param socketData - The address and port number for this client, as a SocketData object.
   */
  public Client(SocketData socketData) {
    this.socketData = socketData;
    this.socket = null;
  }

  /**
   * Opens the socket connection for this client. If errors are encountered, prints a message
   * accordingly and exits w/status 1.
   */
  public void connect() {
    try {
      this.socket = new Socket(this.socketData.getAddress(), this.socketData.getPort());
    } catch (IOException e) {
      System.out.println(String.format("Error connecting to socket on port %s. (The server may be "
           + "down.) Exiting.", this.socketData.getPort()));
      exit(1);
    }
  }

  /**
   * Given a string of output text, sends it through the socket. If errors are encountered, prints a
   * message accordingly, closes the socket, and exits w/status 1.
   *
   * @param outputText - Text to send through socket, as a String.
   */
  public void send(String outputText) {
    try {
      PrintWriter outputStream = new PrintWriter(this.socket.getOutputStream(), true);
      outputStream.println(outputText);
    } catch (IOException e) {
      System.out.println("I/O Error when opening output stream to server. Exiting.");
      this.end(1);
    }
  }

  /**
   * Reads the message from the socket and returns it as a String. If errors are encountered, prints
   * a message accordingly, closes the socket, and exits w/status 1.
   *
   * @return Message received from socket, as a String.
   */
  public String receive() {
    try {
      BufferedReader inputStream = new BufferedReader(new InputStreamReader(
          this.socket.getInputStream()));
      return inputStream.readLine();
    } catch (IOException e) {
      System.out.println("I/O Error when reading input stream from server. Exiting.");
      this.end(1);
    }
    return null;
  }

  /**
   * Closes the socket and exits with the passed status. If errors are encountered, prints a message
   * accordingly and exits w/status 1.
   *
   * @param status - The status level to exit with, as an int (0 for success, 1 for errors).
   */
  public void end(int status) {
    try {
      this.socket.close();
      exit(status);
    } catch (IOException e) {
      System.out.println("I/O Error when closing socket. Exiting.");
      exit(1);
    }
  }

}
