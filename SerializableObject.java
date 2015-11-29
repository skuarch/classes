package model.util;

import java.io.Serializable;

/**
 * if you don't want to implement Serializable interface used this class in
 * order to cast the object
 *
 * @author skuarch
 */
public class SerializableObject {

    //==========================================================================
    /**
     * create a instance.
     */
    public SerializableObject() {
    } // end SerializableObject

    //==========================================================================
    /**
     * cast object to Serializable
     * @param object Object
     * @return Object
     */
    public Object getSerializableObject(Object object) {
        return (Serializable) object;
    } // end getSerializableObject
    
} // end class
