package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import socketdata.SocketData;

/**
 * TCP Client Program.
 *
 * Connects to server socket, sends a line of user input text to the server, receives it back
 * formatted, and terminates.
 */
public class ClientApp {

  private static final int MAX_CHARS_ALLOWED = 80;
  private static final int NUM_OPTIONAL_ARGS = 2;


  /**
   * Accepts user input from the command line and returns it as a String. String must be between 1
   * and 80 characters (inclusive).
   *
   * @return User input from the command line, as a String.
   */
  public static String getUserInput() {
    try {
      BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
      String inputText = inputStream.readLine();
      while (inputText != null) {
        if (inputText.length() > 0 && inputText.length() <= MAX_CHARS_ALLOWED) {
          return inputText;
        } else {
          System.out.println("String must be between 1 and 80 characters (inclusive).\n"
              + "Enter string to format:");
          inputText = inputStream.readLine();
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading from user input. Exiting.");
    }
    return null;
  }

  /**
   * Runs through the following steps using the passed client, terminating the program once
   * complete: - Connects to the server at the socket associated with this client. - Receives a
   * string as user input from the command line. - Sends the string to the socket. - Receives the
   * string back from the socket, formatted. - Ends the program.
   *
   * @param client - The client to run the program on.
   */
  public static void run(Client client) {
    client.connect();
    System.out.println("Connected to server successfully. Enter string to format:");
    String message = getUserInput();
    if (message == null) {
      client.end(1);
    } else {
      client.send(message);
      String response = client.receive();
      System.out.println("Server response:\n" + response + "\n");
      System.out.println("Program complete. Closing connection and terminating.");
      client.end(0);
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
    ClientApp.run(new Client(socketData));


  }
}
