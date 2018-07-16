package com.epam.garmash.web.servlets;

import com.epam.garmash.beans.User;
import com.epam.garmash.dto.UserDto;
import com.epam.garmash.service.user.UserService;
import com.epam.garmash.web.AppContextConstants;
import com.epam.garmash.web.Paths;
import com.epam.garmash.web.ViewConstants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String ERROR_KEY = "Error";
    private static final String WRONG_EMAIL_LOGIN = "Wrong email or login. Please try again";

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(AppContextConstants.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ViewConstants.LOGIN_ERRORS, req.getSession().getAttribute(ViewConstants.LOGIN_ERRORS));
        req.setAttribute(ViewConstants.POPUP_LOGIN, ViewConstants.POPUP_LOGIN);
        HttpSession session;
        if ((session = req.getSession(false)) != null) {
            session.removeAttribute(ViewConstants.LOGIN_ERRORS);
        }
        req.getRequestDispatcher(Paths.HOME_SERVLET).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path;
        String login = req.getParameter(ViewConstants.LOGIN);
        String password = req.getParameter(ViewConstants.PASSWORD);
        UserDto loginForm = new UserDto(login, password);
        Optional<User> signedInUser = userService.getUserByEmail(loginForm);

        if (signedInUser.isPresent() && passwordsAreEqual(loginForm.getPass(), signedInUser.get().getPass())) {
            req.getSession().setAttribute(ViewConstants.SIGNED_IN_USER, signedInUser.get());
            path = Paths.HOME_SERVLET;
        } else {
            Map<String, String> loginErrors = new HashMap<>();
            loginErrors.put(ERROR_KEY, WRONG_EMAIL_LOGIN);
            req.getSession().setAttribute(ViewConstants.LOGIN_ERRORS, loginErrors);
            path = Paths.LOGIN;
        }
        resp.sendRedirect(path);
    }

    private boolean passwordsAreEqual(String inputPass, String actualPass) {
        return Objects.equals(actualPass, inputPass);
    }


}
