package controller.application;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
public class BaseController {

    private static final Logger LOGGER = Logger.getLogger(BaseController.class);    

    //==========================================================================
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    //==========================================================================
    public void setContentType(String contenType) {

        try {

            HttpServletResponse response = getResponse();

            if (response != null) {
                response.setContentType(contenType + ";charset=UTF-8");
            }

        } catch (Exception e) {
            LOGGER.error("BaseController.setContentType()", e);
        }

    }

    //==========================================================================
    public void setHeaderNoChache() {

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

    //==========================================================================
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable t, HttpServletRequest request, Exception exception) {

        LOGGER.error(" error api ", t);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.addObject("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        if (request.getContentType() == null || request.getContentType().length() < 1) {
            mav.setViewName("application/errorHtml");
        } else if (request.getContentType().contains("json")) {
            mav.setViewName("application/errorJson");
        } else if (request.getContentType().contains("xml")) {
            mav.setViewName("application/errorXml");
        } else if (request.getContentType().contains("html")) {
            mav.setViewName("application/errorHtml");
        } else {
            mav.setViewName("application/errorTextPlain");
        }

        return mav;
    }

    //==========================================================================
    public static <T> Logger getLogger(Class<T> c) {
        return Logger.getLogger(c.getName());
    }

    //==========================================================================
    public void setStatus(HttpStatus status) {

        HttpServletResponse response;

        try {

            response = getResponse();

            if (response != null && status != null) {
                response.setStatus(status.value());
            }

        } catch (Exception e) {
            LOGGER.error("BaseController.getResponse()", e);
        }

    }

    //==========================================================================
    private HttpServletResponse getResponse() {

        HttpServletResponse response = null;

        try {

            ServletWebRequest servletContainer = (ServletWebRequest) RequestContextHolder.getRequestAttributes();
            response = servletContainer.getResponse();

        } catch (Exception e) {
            LOGGER.error("BaseController.getResponse()", e);
        }

        return response;

    }

}
