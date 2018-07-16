package com.epam.garmash.web.servlets;

import com.epam.garmash.service.captcha.CaptchaService;
import com.epam.garmash.web.AppContextConstants;
import com.epam.garmash.web.Paths;
import com.epam.garmash.web.ViewConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(HomeServlet.class);

    private CaptchaService captchaService;

    @Override
    public void init(ServletConfig config) {
        captchaService = (CaptchaService) config.getServletContext().getAttribute(AppContextConstants.CAPTCHA_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String captchaID = captchaService.setUpCaptcha();
        LOG.debug("Captcha ID generated in home servlet " + captchaID);
        req.setAttribute(ViewConstants.CAPTCHA_ID, captchaID);
        req.getRequestDispatcher(Paths.INDEX_JSP).forward(req, resp);
    }

}
