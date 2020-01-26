package login;

import javax.faces.application.ResourceHandler;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*")
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (isResourceReq(req) || isSiteAllowed(req) || isUserLogged(req)) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(getServletContext().getContextPath() + "/login.xhtml");
        }
    }

    private boolean isUserLogged(HttpServletRequest req) {
        var session = req.getSession(false);
        return session != null && session.getAttribute("username") != null;
    }

    private boolean isSiteAllowed(HttpServletRequest req) {
        return req.getRequestURI().equals(req.getContextPath() + "/login.xhtml") ||
                req.getRequestURI().equals(req.getContextPath() + "/register.xhtml") ||
                req.getRequestURI().equals(req.getContextPath() + "/index.xhtml") ||
                req.getRequestURI().equals(req.getContextPath() + "/addArticle.xhtml") ||
                req.getRequestURI().equals(req.getContextPath() + "/editArticle.xhtml");
    }

    private boolean isResourceReq(HttpServletRequest req) {
        return req.getRequestURI().startsWith(
                req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
    }
}