package com.ase.application.Filter;

import com.ase.application.Repository.UserRepository;
import com.ase.application.Service.UserService;
import com.ase.application.Service.UserServiceImpl;
import com.ase.application.entity.User;
import com.ase.application.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AuthenticationFilter implements javax.servlet.Filter{

    private static final String[] EXCLUDED_URL_LIST = new String[]{"/login", "/signUp","/entry", "/logout", "/forgotPassword", "/validate"};
    private static final String[] RESOURCE_URL_PRFIX = new String[]{"/js/", "/css/", "/img/"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Inside DO Filter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getServletPath();
        HttpSession session = httpRequest.getSession(false);

        if (!new ArrayList<>(Arrays.asList(EXCLUDED_URL_LIST)).contains(url)
                && new ArrayList<>(Arrays.asList(RESOURCE_URL_PRFIX)).stream().noneMatch(url::startsWith) &&
                (Objects.isNull(session) || Objects.isNull(session.getAttribute("currentUser")))) {
            httpResponse.sendRedirect("/login");
        } else {
            chain.doFilter(request, response);
        }
    }
}
