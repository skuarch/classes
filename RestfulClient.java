package model.net.restful;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import util.IOUtilities;

/**
 * client restful, in order to used this class you need open the connection,
 * send or receive data and close the connection. This class send and receive
 * jsons
 *
 * @author skuarch
 */
public class RestfulClient {

    public static final String POST_METHOD = "POST";
    public static final String GET_METHOD = "GET";
    public static final String PUT_METHOD = "PUT";
    public static final String DELETE_METHOD = "DELETE";
    private String path = null;
    private URL url = null;
    private HttpURLConnection hurlc = null;
    private String requestMethod;
    private OutputStream outputStream = null;
    private InputStream inputStream = null;
    private BufferedReader bufferedReader = null;

    //==========================================================================
    /**
     * create a instance.
     *
     * @param path String url of webservice
     * @param requestMethod String POST,GEt,PUT,DELETE
     */
    public RestfulClient(String path, String requestMethod) {

        this.path = path;
        this.requestMethod = requestMethod;

    } // end RestfulClient

    //==========================================================================
    /**
     * open connection with web service. this connection is for send json and
     * receive json
     *
     * @throws MalformedURLException
     * @throws IOException
     */
    public void openConnection() throws MalformedURLException, IOException {

        if (path == null || path.length() < 1) {
            throw new NullPointerException("path is null or empty");
        }

        if (requestMethod == null || requestMethod.length() < 1) {
            throw new NullPointerException("requestMethod is null or empty");
        }

        url = new URL(path);
        hurlc = (HttpURLConnection) url.openConnection();
        hurlc.setRequestMethod(requestMethod);
        hurlc.setRequestProperty("Accept", "application/json");
        hurlc.setRequestProperty("Content-Type", "application/json");

    } // end openConnection

    //==========================================================================
    /**
     * write text to web server.
     *
     * @param text String
     * @throws IOException
     */
    public void sendText(String text) throws IOException {

        if (text == null || text.length() < 1) {
            throw new NullPointerException("text is null");
        }

        hurlc.setDoOutput(true);
        outputStream = hurlc.getOutputStream();
        outputStream.write(text.getBytes());
        outputStream.flush();

    } // end sendText

    //==========================================================================
    /**
     * receive text from web service.
     *
     * @return String
     * @throws IOException
     */
    public String receiveText() throws IOException {

        String tmp = null;
        StringBuilder stringBuilder = new StringBuilder();
        inputStream = hurlc.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        while ((tmp = bufferedReader.readLine()) != null) {
            stringBuilder.append(tmp);
        }

        return stringBuilder.toString();

    } // end receiveText

    //==========================================================================
    /**
     * close connection.
     */
    public void closeConnection() {
        IOUtilities.disconnectURL(hurlc);
        IOUtilities.closeOutputStream(outputStream);
        IOUtilities.closeInputStream(inputStream);
        IOUtilities.closeBufferedReader(null);
    } // end closeConnection
} // end class 
