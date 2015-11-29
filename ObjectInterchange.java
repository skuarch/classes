package model.net;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.UnexpectedException;
import model.util.IOUtilities;

/**
 * class to send and receive objects using sockets.
 *
 * @author skuarch
 */
public class ObjectInterchange {

    private Socket socket = null;
    private OutputStream outputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private InputStream inputStream = null;
    private ObjectInputStream objectInputStream = null;
    private String host = null;
    private int port;
    private int timeout;

    //==========================================================================
    /**
     * create a instance
     */
    public ObjectInterchange() {
    } // end ObjectInterchange

    //==========================================================================
    /**
     * create a instance with params
     *
     * @param host String ip address, domain or hostname
     * @param port int number of port
     * @param timeOut int time for close the connection
     */
    public ObjectInterchange(String host, int port, int timeOut) {
        this.host = host;
        this.port = port;
        this.timeout = timeOut;
    } // end ObjectInterchange

    //==========================================================================
    /**
     * create connection with the host.
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    public void connect() throws UnknownHostException, IOException {

        if (host == null) {
            throw new NullPointerException("host is null");
        }

        if (port <= 1) {
            throw new NullPointerException("port is less than 0");
        }

        if (timeout < 1) {
            throw new NullPointerException("timeOut is less than 0");
        }

        socket = new Socket(host, port);
        socket.setSoTimeout(timeout);

    } // end connect

    //==========================================================================
    /**
     * close the connection
     */
    public void closeConnection() {
        IOUtilities.closeOutputStream(outputStream);
        IOUtilities.closeOutputStream(objectOutputStream);
        IOUtilities.closeInputStream(inputStream);
        IOUtilities.closeInputStream(objectInputStream);
        IOUtilities.closeSocket(socket);
    } // end closeConnection

    //==========================================================================
    /**
     *  send a object to remote host
     *
     * @param object Object serializable object
     * @throws IOException
     */
    public void sendObject(Object object) throws IOException {
        checkSocket();
        outputStream = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    } // end sendObject

    //==========================================================================
    /**
     * receive a object from remote host
     *
     * @return serializable Object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object receiveObject() throws IOException, ClassNotFoundException, EOFException {

        checkSocket();
        Object object = null;

        try {
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            object = objectInputStream.readObject();
        } catch (EOFException eofe) {
            throw new EOFException("remote host closed the connection");
        }

        return object;
    } // end receiveObject 

    //==========================================================================
    /**
     * validate if is possible to do the connection.
     *
     * @throws UnexpectedException
     */
    private void checkSocket() throws UnexpectedException {
        if (socket == null || socket.isClosed()) {
            throw new UnexpectedException("illegal operation socket is closed or null");
        }
    } // end checkSocket

    //==========================================================================
    /**
     * return the host
     *
     * @return String
     */
    public String getHost() {
        return host;
    } // end getHost

    //==========================================================================
    /**
     * set the host
     *
     * @param host String
     */
    public void setHost(String host) {
        this.host = host;
    } // end setHost

    //==========================================================================
    /**
     * return a port
     *
     * @return int
     */
    public int getPort() {
        return port;
    } // end getPort

    //==========================================================================
    /*
     * set the port
     */
    public void setPort(int port) {
        this.port = port;
    } // end setPort   

    //==========================================================================
    /**
     * return a socket
     *
     * @return Socket
     */
    public Socket getSocket() {
        return socket;
    } // end getSocket    

    //==========================================================================
    /**
     * set socket
     *
     * @param socket Socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    } // end setSocket

    //==========================================================================
    /**
     * return timeout
     *
     * @return int
     */
    public int getTimeout() {
        return timeout;
    } // end getTimeout

    //==========================================================================
    /**
     * set timeout
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    } // end setTimeout
} // end class
