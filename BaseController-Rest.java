package controller.application;

import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import model.bean.GenericMessage;
import model.logic.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * this class is designed to be extended in any controller.<br>
 * all the controllers should extend this class.<br>
 * if you want to use GenericMessage getters this class must be inject with
 * <code>@Autowired</code>
 *
 * @author skuarch
 *
 */
public class BaseController {

    private static final Logger LOGGER = Logger.getLogger(BaseController.class);
    private static final String OPERATION_SUCCESSFULLY_COMPLETED = "operation successfully completed";

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
    protected final GenericMessage handleException(String message, Exception exception, Class<?> c) {

        if (message == null || message.length() < 1) {
            throw new IllegalArgumentException("message is null or empty");
        }

        if (exception == null) {
            throw new IllegalArgumentException("exception is null");
        }

        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }

        Logger logger = Logger.getLogger(c);
        logger.error(message, exception);
        HttpServletResponse response = getResponse();
        genericMessage.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        genericMessage.setMessage(exception.getMessage());

        if (response != null) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setHeader("message", message);
        }

        return genericMessage;
    }

    // ==========================================================================
    /**
     * getter of GenericMessage.
     *
     * @return GenericMessage
     */
    protected final GenericMessage getGenericMessage() {
        return genericMessage;
    }

    // ==========================================================================
    /**
     * return a GenericMessage setting with message of successfully.
     *
     * @param id long
     * @return GenericMessage
     */
    protected final GenericMessage getSuccessfulMessage(long id) {
        genericMessage.setId(id);
        genericMessage.setCode(Constants.HTTP_OK);
        genericMessage.setMessage(OPERATION_SUCCESSFULLY_COMPLETED);
        return genericMessage;
    }

    // ==========================================================================
    /**
     * getter of GenericMessage.
     *
     * @return GenericMessage
     */
    protected final GenericMessage getSuccessfulMessage() {
        genericMessage.setCode(Constants.HTTP_OK);
        genericMessage.setMessage(OPERATION_SUCCESSFULLY_COMPLETED);
        return genericMessage;
    }

    //==========================================================================
    /**
     * set content type of the request.
     *
     * @param contentType String
     */
    protected final void setContentType(String contentType) {

        if (contentType == null || contentType.length() < 1) {
            throw new IllegalArgumentException("contentType is null or empty");
        }

        try {

            HttpServletResponse response = getResponse();

            if (response != null) {
                response.setContentType(contentType + ";charset=UTF-8");
            }

        } catch (Exception e) {
            LOGGER.error("BaseController.setContentType()", e);
        }

    }

    //==========================================================================
    /**
     * get the response to the request.
     *
     * @return HttpServletResponse or null
     */
    private HttpServletResponse getResponse() {

        HttpServletResponse response = null;

        try {

            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            if (requestAttributes != null) {
                response = ((ServletRequestAttributes) requestAttributes).getResponse();
            }

        } catch (Exception e) {
            LOGGER.error("BaseController.getResponse()", e);
        }

        return response;

    }

    //==========================================================================
    /**
     * avoid cache in the current request.
     */
    protected final void setHeaderNoChache() {

        try {

            HttpServletResponse response = getResponse();

            if (response != null) {
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
            }

        } catch (Exception e) {
            LOGGER.error("BaseController.setHeaderNoChache()", e);
        }

    }

}
