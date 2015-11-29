package interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.RequestBean;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequestWrapper;
import model.database.HibernateConnection;

/**
 *
 * @author skuarch
 */
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(RequestInterceptor.class);

    //==========================================================================
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        createRequestBean(request);
        return true;
    }

    //==========================================================================
    /**
     * empty.
     *
     * @param request
     * @param response
     * @param o
     * @param mav
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav) throws Exception {
        //empty
    }

    //==========================================================================
    /**
     * empty.
     *
     * @param hsr
     * @param hsr1
     * @param o
     * @param excptn
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        //empty
    }

    //==========================================================================
    /**
     * save request into DB.
     *
     * @param req HttpServletRequest
     */
    private void createRequestBean(final HttpServletRequest req) {

        try {

            new Thread(new Runnable() {

                @Override
                public void run() {
                    RequestBean rb = new RequestBean();
                    HttpServletRequestWrapper request = new HttpServletRequestWrapper(req);

                    if (request.getRemoteHost() != null) {
                        rb.setRemoteHost(request.getRemoteHost());
                    }

                    if (request.getContentType() != null) {
                        rb.setContentType(request.getContentType());
                    }

                    if (request.getRemoteHost() != null) {
                        rb.setRemoteHost(request.getRemoteHost());
                    }

                    if (request.getLocalAddr() != null) {
                        rb.setLocalAddress(request.getLocalAddr());
                    }

                    if (request.getMethod() != null) {
                        rb.setMethod(request.getMethod());
                    }

                    if (request.getPathInfo() != null) {
                        rb.setPathInfo(request.getPathInfo());
                    }

                    if (request.getProtocol() != null) {
                        rb.setProtocol(request.getProtocol());
                    }

                    if (request.getRemoteUser() != null) {
                        rb.setRemoteUser(request.getRemoteUser());
                    }

                    if (request.getRequestURI() != null) {
                        rb.setUri(request.getRequestURI());
                    }

                    if (request.getScheme() != null) {
                        rb.setScheme(request.getScheme());
                    }

                    if (request.getServletPath() != null) {
                        rb.setServletPath(request.getServletPath());
                    }

                    rb.setContentLength(request.getContentLength());

                    if (request.getRequestURL().toString() != null) {
                        rb.setUrl(request.getRequestURL().toString());
                    }

                    rb.setRemotePort(request.getRemotePort());

                    rb.setLocalPort(request.getLocalPort());

                    if (request.getHeaderNames() != null) {
                        Enumeration e = request.getHeaderNames();
                        StringBuilder sb = new StringBuilder();
                        while (e.hasMoreElements()) {
                            sb.append(e.nextElement().toString()).append(", ");
                        }
                        rb.setHeaderNames(sb.toString());
                    }

                    if (request.getAttributeNames() != null) {
                        Enumeration e = request.getAttributeNames();
                        StringBuilder sb = new StringBuilder();
                        while (e.hasMoreElements()) {
                            sb.append(e.nextElement().toString()).append(", ");
                        }
                        rb.setAttributesNames(sb.toString());
                    }

                    if (request.getAuthType() != null) {
                        rb.setAuthType(request.getAuthType());
                    }

                    if (request.getCharacterEncoding() != null) {
                        rb.setCharacterEncoding(request.getCharacterEncoding());
                    }

                    if (request.getScheme() != null) {
                        rb.setScheme(request.getScheme());
                    }

                    if (request.getLocale() != null) {
                        rb.setLocale(request.getLocale().toString());
                    }

                    if (request.getParameterNames() != null) {
                        Enumeration e = request.getParameterNames();
                        StringBuilder sb = new StringBuilder();
                        while (e.hasMoreElements()) {
                            sb.append(e.nextElement().toString()).append(", ");
                        }
                        rb.setParameterNames(sb.toString());
                    }

                    if (request.getHeader("User-Agent") != null) {
                        rb.setUserAgent(request.getHeader("User-Agent"));
                    }

                    if (request.getHeader("Accept-Encoding") != null) {
                        rb.setAcceptEncoding(request.getHeader("Accept-Encoding"));
                    }

                    if (request.getHeader("Origin") != null) {
                        rb.setOrigin(request.getHeader("Origin"));
                    }

                    if (request.getHeader("Accept") != null) {
                        rb.setAccept(request.getHeader("Accept"));
                    }

                    if (request.getHeader("Connection") != null) {
                        rb.setConnection(request.getHeader("Connection"));
                    }

                    new HibernateConnection().createObject(rb);
                }
            }).start();

        } catch (Exception e) {
            LOGGER.error("RequestInterceptor.createRequestBean", e);
        }
    }

} // end class
