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
public class NotSupported extends BaseController {

    //==========================================================================
    @ResponseBody
    @RequestMapping(value = {"/notSupported"})
    public ModelAndView welcome(HttpServletRequest request) throws ServletException {

        if (request.getContentType() == null || request.getContentType().equals("")) {
            return new ModelAndView("application/notSupportedHtml");
        }

        if (request.getContentType().contains("application/json")) {
            return new ModelAndView("application/notSupportedJson");
        }

        return new ModelAndView("application/notSupportedHtml");

    }

}
