package com.epam.garmash.web.servlets;

import com.epam.garmash.web.Paths;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session;
        if ((session = req.getSession(false)) != null) {
            session.invalidate();
        }
        resp.sendRedirect(Paths.HOME_SERVLET);
    }

}
