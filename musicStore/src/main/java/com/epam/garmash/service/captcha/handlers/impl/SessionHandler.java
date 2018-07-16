package com.epam.garmash.service.captcha.handlers.impl;

import com.epam.garmash.service.captcha.Captcha;
import com.epam.garmash.service.captcha.handlers.CaptchaHandler;
import com.epam.garmash.web.ViewConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionHandler implements CaptchaHandler {

    @Override
    public String init() {
        return "";
    }

    @Override
    public void storeCaptcha(HttpServletRequest req, HttpServletResponse resp, Captcha captcha) {
        req.getSession().setAttribute(ViewConstants.CAPTCHA, captcha.getCaptchaValue());
    }

    @Override
    public boolean checkCaptcha(HttpServletRequest req) {
        return req.getSession().getAttribute(ViewConstants.CAPTCHA).equals(req.getParameter(ViewConstants.CAPTCHA_INPUT_FILED));
    }
}