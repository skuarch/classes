package controller.application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author skuarch
 */
@Controller
public class NotFound extends BaseController {

    //==========================================================================
    @ResponseBody
    @RequestMapping(value = {"/notFound"})    
    public ModelAndView welcome(HttpServletRequest request) throws ServletException {

        if (request.getContentType() == null || request.getContentType().equals("")) {
            return new ModelAndView("application/notFoundHtml");
        }

        if (request.getContentType().contains("application/json")) {
            return new ModelAndView("application/notFoundJson");
        }

        return new ModelAndView("application/notFoundHtml");

    }

}
