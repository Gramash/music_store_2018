package com.epam.garmash.web.servlets;

import com.epam.garmash.service.captcha.Captcha;
import com.epam.garmash.service.captcha.CaptchaService;
import com.epam.garmash.web.AppContextConstants;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CaptchaServlet.class);
    private static final String LOG_CAPTCHA_CREATION = "new captcha created in captcha servlet ";
    private static final String LOG_CAPTCHA_VALUE = "captcha value ";

    private static final String IMAGE_FORMAT = "jpeg";
    private static final String RESPONSE_CONTENT_TYPE = "image/jpg";

    private CaptchaService captchaService;

    @Override
    public void init(ServletConfig config) {
        captchaService = (CaptchaService) config.getServletContext().getAttribute(AppContextConstants.CAPTCHA_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(RESPONSE_CONTENT_TYPE);
        Captcha captcha = captchaService.storeCaptcha(request, response);
        LOG.debug(LOG_CAPTCHA_CREATION);
        LOG.debug(LOG_CAPTCHA_VALUE + captcha.getCaptchaValue());

        OutputStream osImage = response.getOutputStream();
        ImageIO.write(captcha.getCaptchaImg(), IMAGE_FORMAT, osImage);
        osImage.close();
    }

}
