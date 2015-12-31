package controller.application;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import model.bean.GenericMessage;
import model.logic.Constants;
import org.springframework.http.HttpStatus;

/**
 * this class is designed to be extended in any controller.<br>
 * all the controllers should extend this class.<br>
 * if you want to use GenericMessage getters this class must be inject with <code>@Autowired</code>
 *
 * @author skuarch
 *
 */
public class BaseController {

    @Autowired
    private GenericMessage genericMessage;

    // ==========================================================================
    /**
     * easy way to handle exceptions in controllers.
     *
     * @param message String
     * @param exception Exception
     * @param c Class
     * @return GenericMessage
     */
    public GenericMessage handleException(String message, Exception exception, Class<?> c) {

        Logger logger = Logger.getLogger(c);
        logger.error(message, exception);
        genericMessage.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        genericMessage.setMessage(exception.getMessage());

        return genericMessage;
    }

    // ==========================================================================
    /**
     * easy way to handle exceptions in controllers.
     *
     * @param message String
     * @param exception Exception
     * @param c Class
     */
    public void handleException(Exception exception, String message, Class<?> c) {

        Logger logger = Logger.getLogger(c);
        logger.error(message, exception);

    }

    //==========================================================================
    /**
     * this method set response variable to 500 (INTERNAL_SERVER_ERROR).
     * @param response HttpServletResponse
     * @param exception Exception
     * @param message String
     * @param c Class
     */
    public void handleException(HttpServletResponse response, Exception exception, String message, Class<?> c) {

        Logger logger = Logger.getLogger(c);
        logger.error(message, exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

    // ==========================================================================
    /**
     * getter of GenericMessage.
     *
     * @return GenericMessage
     */
    public GenericMessage getGenericMessage() {
        return genericMessage;
    }

    // ==========================================================================
    /**
     * return a GenericMessage setting with message of successfully.
     *
     * @param id long
     * @return GenericMessage
     */
    public GenericMessage getSuccessfulMessage(long id) {
        genericMessage.setId(id);
        genericMessage.setCode(Constants.HTTP_OK);
        genericMessage.setMessage("operation successfully completed");
        return genericMessage;
    }

    // ==========================================================================
    /**
     * getter of GenericMessage.
     *
     * @return GenericMessage
     */
    public GenericMessage getSuccessfulMessage() {
        genericMessage.setCode(Constants.HTTP_OK);
        genericMessage.setMessage("operation successfully completed");
        return genericMessage;
    }

}
