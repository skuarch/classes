package model.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Bean.
 * @author skuarch
 */
public class GenericMessage implements Serializable {

    private static final long serialVersionUID = 8555172341255856507L;
    private long id;
    private int code;
    private String message;
    private long timestamp = System.currentTimeMillis();
    private String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    //==========================================================================
    /**
     * constructor.
     */
    public GenericMessage() {
    }

    //==========================================================================
    /**
     * contrusctor.
     * @param id long
     * @param code int
     * @param message String
     */
    public GenericMessage(long id, int code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }

    //==========================================================================
    /**
     * getter.
     * @return id
     */
    public long getId() {
        return id;
    }

    //==========================================================================
    /**
     * setter.
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    //==========================================================================
    /**
     * getter.
     * @return code
     */
    public int getCode() {
        return code;
    }

    //==========================================================================
    /**
     * setter.
     * @param code int
     */
    public void setCode(int code) {
        this.code = code;
    }

    //==========================================================================
    /**
     * getter.
     * @return message
     */
    public String getMessage() {
        return message;
    }

    //==========================================================================
    /**
     * setter.
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }    

    //==========================================================================
    /**
     * getter.
     * @return timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    //==========================================================================
    /**
     * setter.
     * @param timestamp long
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    //==========================================================================
    /**
     * getter.
     * @return date 
     */
    public String getDate() {
        return date;
    }

    //==========================================================================
    /**
     * setter.
     * @param date String
     */
    public void setDate(String date) {
        this.date = date;
    }

}
