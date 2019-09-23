package socketdata;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Class holding socket data (port and address).
 */
public class SocketData {

  private static final int DEFAULT_PORT_NUMBER = 1500;
  private int port;
  private InetAddress address;

  /**
   * Constructs a new SocketData object using the default port number and address.
   */
  public SocketData() {
    this.port = DEFAULT_PORT_NUMBER;
    this.address = InetAddress.getLoopbackAddress();
  }

  /**
   * Constructs a new SocketData object using the passed port number and address.
   *
   * @param port - Port number to set this SocketData to, as an int.
   * @param address - Address to set this SocketData to, as an InetAddress object.
   */
  public SocketData(int port, InetAddress address) {
    this.port = port;
    this.address = address;
  }

  /**
   * Returns the port number for this SocketData object.
   *
   * @return Port number, as an int.
   */
  public int getPort() {
    return this.port;
  }

  /**
   * Sets this SocketData's port number to the passed int.
   *
   * @param portInput, port number to set this SocketData's port number to, as an int.
   */
  public void setPort(int portInput) {
    this.port = portInput;
  }

  /**
   * Given a string value of a port number, checks if it can be converted to a number, and if so,
   * sets the port number as it. If not, prints appropriate error message and takes no action.
   *
   * @param portInput, port number to set this SocketData's port number to, as a String.
   */
  public void setPort(String portInput) {
    try {
      this.port = Integer.valueOf(portInput);
    } catch (NumberFormatException e) {
      System.out.println(String.format("An invalid port number was passed. Defaulting to port %d.",
          DEFAULT_PORT_NUMBER));
    }
  }

  /**
   * Returns the InetAddress for this SocketData object.
   *
   * @return Address, as an InetAddress object.
   */
  public InetAddress getAddress() {
    return this.address;
  }

  /**
   * Sets this SocketData's address to the passed InetAddress.
   *
   * @param inetAddress, address to set this SocketData's address to, as an InetAddress.
   */
  public void setAddress(InetAddress inetAddress) {
    this.address = inetAddress;
  }

  /**
   * Given a string value of a host address, checks if it can be converted to an InetAddress, and if
   * so, sets address as it. If not, prints appropriate error message and takes no action.
   *
   * @param hostname, hostname to set this SocketData's InetAddress to, as a String.
   */
  public void setAddress(String hostname) {
    try {
      this.address = InetAddress.getByName(hostname);
    } catch (UnknownHostException e) {
      System.out.println("An unknown host was passed. Defaulting to local loopback address.");
    }
  }


}