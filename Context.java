package model.common;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * start context in localhost
 *
 * @author skuarch
 */
public class Context {

    //==========================================================================    
    /**
     * create a instance.
     */
    public Context() {
    } // end context

    //==========================================================================
    /**
     * start context in localhost
     *
     * @return InitialContext
     * @throws Exception
     */
    public InitialContext getInitialContext() throws NamingException {

        Properties properties = null;
        InitialContext initialContext = null;

        try {

            properties = new Properties();
            properties.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            properties.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            initialContext = new InitialContext(properties);

        } catch (NamingException ne) {
            throw ne;
        }

        return initialContext;

    } // end getInitialContext
    
} //  end class
