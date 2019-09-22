package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) throws IOException {
    System.out.println("Hi, I'm the server.");
    int portNumber = 9999;
    try {
      ServerSocket serverSocket = new ServerSocket(portNumber);
      Socket clientSocket = serverSocket.accept();
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Client.Client connected on port " + portNumber + ". Handling requests.");
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          System.out.println("Received request: " + inputLine + " from " + clientSocket.toString());
          String reversedStr = StringManipulator.reverseChangeCase(inputLine);
          out.println(reversedStr);
        }
    } catch(IOException e) {
      System.out.println("Exception caught when trying to listen on port "
          + portNumber + " or listening for a connection.");

    }


  }

  }
