package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {

  private static final int DEFAULT_PORT_NUMBER = 1500;
  private static final String LOCALHOST_IP = "127.0.0.1";

  public static void main(String[] args) throws IOException {
    System.out.println("Client started on localhost, port 1567.");
    int portNumber = DEFAULT_PORT_NUMBER;

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
