package controller.application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
@Controller
public class ServerError extends BaseController {

    //==========================================================================
    @ResponseBody
    @RequestMapping(value = {"/serverError"})
    public ModelAndView welcome(HttpServletRequest request) throws ServletException {

        ModelAndView mav = new ModelAndView("application/errorHtml");
        mav.addObject("exception", "sorry we haven an error");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        
        if (request.getContentType() == null || request.getContentType().equals("")) {
            mav.setViewName("application/errorHtml");
        }

        if (request.getContentType().contains("application/json")) {
            mav.setViewName("application/errorJson");
        }
        
        return mav;

    }

}
