package ru.mativ.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.mativ.server.session.SessionController;
import ru.mativ.server.session.UserSession;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        SessionController.instance();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest wrappedRequest = new UserRequestWrapper((HttpServletRequest) request);
        auth(wrappedRequest, (HttpServletResponse) response, chain);
    }

    @Override
    public void destroy() {
        //do nothing
    }

    private void auth(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try {
            SessionController.instance().updateUserSession((UserSession) request.getUserPrincipal());
        } catch (Exception e) {
            sendErrorAuth(response, e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorAuth(HttpServletResponse response, String explanation) {
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            response.getWriter().write("Authentication error: " + explanation);
        } catch (IOException e) {
            //do nothing
        }
    }
}
