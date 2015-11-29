package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * filter to avoid cache.
 *
 * @author skuarch
 */
public final class NoCacheFilter implements Filter {

    //==========================================================================
    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

        chain.doFilter(request, response);
    }

    //==========================================================================
    @Override
    public void destroy() {
        //empty, nothing to do here
    }

    //==========================================================================
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        //empty, nothing to do here
    }
}
