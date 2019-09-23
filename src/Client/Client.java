package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

  /**
   * The goal of this assignment is to implement a TCP client and server. You can use Java. Your TCP
   * client/server will communicate over the network and exchange data. You can use localhost to
   * test your work.
   *
   * The server will start in passive mode listening for a transmission from the client. The client
   * will then start and contact the server (on a given IP address and port number). The client will
   * pass the server a string (eg: “network”) up to 80 characters in length.
   *
   * On receiving a string from a client, the server should: 1) reverse all the characters, and 2)
   * reverse the capitalization of the strings (“network” would now become “KROWTEN”). The server
   * should then send the string back to the client.
   *
   * The client will display the received string and exit.
   *
   *
   * TODO: Javadoc written (all classes) TODO: Refactor client and server classes TODO: Close
   * connection after formatted string sent/received TODO: Ensure follows TCP protocol.
   */
  public static void main(String[] args) throws IOException {
    System.out.println("Client started on localhost, port 1567.");
    int portNumber = 1500;
    String hostName = "127.0.0.1";
    try {
      Socket socket = new Socket(hostName, portNumber);
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Connected to server. Enter line of text to format:");
      String userInput;
      while ((userInput = stdIn.readLine()) != null) {
        out.println(userInput);
        System.out.println("Response from server: " + in.readLine());
      }
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to " + hostName);
      System.exit(1);

    }


  }

}
